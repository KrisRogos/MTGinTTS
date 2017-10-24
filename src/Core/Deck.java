package Core;

import Core.TTS.SavedObject;
import org.apache.commons.lang3.math.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deck implements Runnable {

    public enum LoadingState {
        NOT_READY,
        SETTING_UP,
        IDLE,
        LOADING_FILE,
        LOADING_CARD_DATA,
        LOADING_CARD_IMAGES,
        SAVING_IMAGE,
        SAVING_DECK_CONVERSION,
        SAVING_DECK_EXPORT,
        CLEAN_UP
    }

    // exception thrown if there is an issue loading in the file
    public class DeckLoaderException extends Exception {

    }

    // structure which holds the cards in the deck
    public class S_DeckCard {
        public MTGCard card;
        public Integer count;
        public int xLoc;
        public int yLoc;
    }

    private Thread t; // thread
    private boolean m_Alive; // thread status
    private String m_DeckFile; // path to deck file
    private String m_OutDir; // path to output directory
    private LoadingState m_State = LoadingState.NOT_READY; // status of the loader
    public Lock m_StateLock;  // lock for the loader status
    private boolean m_IsComplete; // indicator if the deck is ready for export

    private List<String> m_DeckList; // lines of the deck list
    private int m_NumberOfLines; // count of lines in the deck list
    private int m_CurrentLine; // currently processed line from the deck list

    static final private int kCardWidth = 223; // width of one card
    static final private int kCardHeight = 311; // height of one card
    static final public int kWidth = kCardWidth*10; // width of the deck image
    static final public int kHeight = kCardHeight*7; // height of the deck image

    private Vector<S_DeckCard> m_Cards; // unique cards in deck
    private int m_CurrentCard; // card for which the image is being currently processed
    private int m_DeckSize; // total number of cards in the deck (including repeated ones)
    private BufferedImage m_Image; // deck image
    private Graphics m_Graphics; // used to draw the card images onto the deck image

    private SavedObject m_SaveObject; // stores the deck in a format friendly to json export

    public LoadingState getState() {
        return m_State;
    }

    public Deck(String a_DeckName, String a_Directory) {
        m_State = LoadingState.SETTING_UP;
        m_DeckFile = a_DeckName;
        m_OutDir = a_Directory;
        m_Cards = new Vector<S_DeckCard>();
        m_DeckSize = 0;
        m_SaveObject = new SavedObject();

        m_Alive = true;
        m_StateLock = new ReentrantLock();

        // load the card database
        System.out.println("Opening card database");
        CardLoader cardLoader = CardLoader.GetDeckLoader();

        // create the deck image
        m_Image = new BufferedImage(kWidth, kHeight, BufferedImage.TYPE_INT_RGB);
        m_Graphics = m_Image.getGraphics();
        m_State = LoadingState.IDLE;
    }


    @Override
    public void run() {
        while (m_Alive) {
            try {
                if(m_StateLock.tryLock()) {
                    try {
                        switch (m_State) {
                            case NOT_READY:
                            case SETTING_UP:
                                // wait until ready
                                Thread.sleep(50);
                                break;
                            case IDLE:
                                // wait for a task
                                if (!m_IsComplete && m_DeckFile != null && m_DeckFile != "")
                                {
                                    System.out.println("Loading " + m_DeckFile);
                                    m_State = LoadingState.LOADING_FILE;
                                }
                                break;
                            case LOADING_FILE:
                                // read in the file in to m_DeckList
                                LoadFile();

                                // the number of lines and current line are prepared to be used by the Loading Card Data state
                                m_NumberOfLines = m_DeckList.size();
                                m_CurrentLine = 0;

                                // move to next state
                                System.out.println("Looking for card data ");
                                m_State = LoadingState.LOADING_CARD_DATA;
                                break;
                            case LOADING_CARD_DATA:
                                // as long as there are more lines to process, process the next one and move on
                                if (m_CurrentLine < m_NumberOfLines) {
                                    ProcessLine(m_DeckList.get(m_CurrentLine));
                                    m_CurrentLine++;
                                }
                                else {
                                    // move to image drawing
                                    System.out.println("Looking for card images");
                                    m_CurrentCard = 0;
                                    m_State = LoadingState.LOADING_CARD_IMAGES;
                                }
                                break;
                            case LOADING_CARD_IMAGES:
                                // process one card image at a time
                                if (m_CurrentCard < m_Cards.size()) {
                                    GetCardImage(m_Graphics, m_Cards.get(m_CurrentCard));
                                    m_CurrentCard++;
                                }
                                else {
                                    // move to image export
                                    System.out.println("Saving image");
                                    m_State = LoadingState.SAVING_IMAGE;
                                }
                                break;
                            case SAVING_IMAGE:
                                // save the image
                                try {
                                    ImageIO.write(m_Image, "JPG", new File (m_OutDir, "DeckImage.jpg"));
                                    System.out.println("Image saved");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println("Image failed to save");
                                }

                                // move to deck saving
                                System.out.println("Saving deck file");
                                m_CurrentCard = 0;
                                m_State = LoadingState.SAVING_DECK_CONVERSION;
                            case SAVING_DECK_CONVERSION:
                                // set up the save structure


                                // process one unique card at a time

                                //TODO implement deck saving to json feature


                                // move to clean up
                                m_State = LoadingState.SAVING_DECK_EXPORT;
                                break;
                            case SAVING_DECK_EXPORT:
                                // move to clean up
                                m_State = LoadingState.CLEAN_UP;
                                break;
                            case CLEAN_UP:
                                m_IsComplete = true;
                                m_State = LoadingState.IDLE;
                                break;
                        }
                    }
                    finally {
                        m_StateLock.unlock();
                    }

                }
                Thread.sleep(50);
            }
            catch (Exception e) {
                m_Alive = false;
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (m_State == LoadingState.IDLE) {
            m_IsComplete = false;
            System.out.println("Starting " + m_DeckFile + " loader");

            if (t == null) {
                t = new Thread(this);
                t.start();
            }
        }
    }

    public boolean IsTaskComplete() {
        return m_IsComplete;
    }

    private void LoadFile() {
        // load lines from file
        m_DeckList = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(m_DeckFile));
            String line;
            while ((line = reader.readLine()) != null)
            {
                m_DeckList.add(line);
            }

            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Read exception occurred with %s.", m_DeckFile);
            e.printStackTrace();;
        }
    }

    private void GetCardImage(Graphics g, S_DeckCard card)
    {
        try {
            System.out.println("Drawing image for " + card.card.getName());
            URL url = new URL ("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + card.card.getMultiverseid() + "&type=card");
            BufferedImage cardImage = ImageIO.read(url);

            g.drawImage(cardImage, card.xLoc * kCardWidth, card.yLoc * kCardHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    private void GetCardImage(Graphics g, int xLoc, int yLoc, int id)
    {
        try {
            System.out.println("Drawing image for " + m_Cards.elementAt(id).card.getName());
            URL url = new URL ("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + m_Cards.elementAt(id).card.getMultiverseid() + "&type=card");
            BufferedImage cardImage = ImageIO.read(url);

            g.drawImage(cardImage, xLoc * kCardWidth, yLoc * kCardHeight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void ProcessLine(String a_Line)
    {
        // ignore empty lines and comments
        if (!a_Line.isEmpty() && a_Line.charAt(0) != '#')
        {
            String firstWord = a_Line.split(" ")[0]; // extract the first word

            // make sure it's in lower case
            firstWord.toLowerCase();
            int firstLenght = firstWord.length() - 1;

            // if there is an x at the end of it, ignore it
            if (firstWord.charAt(firstLenght) == 'x')
            {
                firstWord = firstWord.substring(0, firstLenght);
            }

            int cardCount;

            // check if the first word is a number, if it is remove it from the line and set the card count to it,
            // otherwise set count to 1
            if (NumberUtils.isCreatable(firstWord))
            {
                cardCount = Integer.parseInt(firstWord);
                a_Line = a_Line.substring(firstLenght+2, a_Line.length());
            }
            else
            {
                cardCount = 1;
            }

            // find the card
            AddCard(cardCount, a_Line);

        }
        else
        {

        }
    }

    public void AddCard (Integer a_Count, String a_Name)
    {
        S_DeckCard dc = new S_DeckCard();
        dc.card = CardLoader.LoadCard(a_Name);
        dc.count = a_Count;
        dc.xLoc = m_Cards.size()%10;
        dc.yLoc = m_Cards.size()/10;

        // if the card wasn't found display an error
        if (dc.card == null) {
            System.out.println("Could not find: " + a_Name);
        }
        else
        {
            m_Cards.add(dc);
            m_DeckSize += a_Count;

            System.out.println("Added " + a_Count + " " + dc.card.getName() + " with art by " + dc.card.getArtist() + " to the Deck. " + m_Cards.size() + " unique cards found. " + m_DeckSize + " cards in the deck.");
        }
    }

    public void AddCard (Integer a_Count, String a_Name, String a_Set)
    {
        /*
        S_DeckCard dc = new S_DeckCard();
        dc.card = new MTGCard();
        dc.card.Load(a_Name, a_Set);
        dc.count = a_Count;

        m_Cards.add(dc);
        */
    }

}
