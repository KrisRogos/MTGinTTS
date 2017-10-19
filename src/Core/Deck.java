package Core;

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

public class Deck implements Runnable {

    public enum LoadingState {
        NOT_READY,
        SETTING_UP,
        IDLE,
        LOADING_FILE,
        LOADING_CARD_DATA,
        LOADING_CARD_IMAGES,
        SAVING_IMAGE,
        SAVING_DECK,
        CLEAN_UP
    }

    // exception thrown if there is an issue loading in the file
    public class DeckLoaderException extends Exception {

    }

    // structure which holds the cards in the deck
    public class S_DeckCard {
        public MTGCard card;
        public Integer count;
    }

    private Thread t; // thread
    private boolean m_Alive; // thread status
    private String m_DeckFile; // path to deck file
    private String m_OutDir; // path to output directory
    private LoadingState m_State; // status of the loader
    public Lock m_StateLock;  // lock for the loader status
    private boolean m_IsComplete; // indicator if the deck is ready for export

    private List<String> m_DeckList; // lines of the deck list
    private int m_NumberOfLines;
    private int m_CurrentLine;

    static final private int kCardWidth = 223; // width of one card
    static final private int kCardHeight = 311; // height of one card
    static final public int kWidth = kCardWidth*10; // width of the deck image
    static final public int kHeight = kCardHeight*7; // height of the deck image

    private Vector<S_DeckCard> m_Cards; // unique cards in deck
    private int m_DeckSize; // total number of cards in the deck (including repeated ones)
    private BufferedImage m_Image; // deck image

    public LoadingState getState() {
        return m_State;
    }

    public Deck(String a_DeckName, String a_Directory) {

        m_DeckFile = a_DeckName;
        m_OutDir = a_Directory;
        m_Cards = new Vector<S_DeckCard>();
        m_DeckSize = 0;

        // load the card database
        CardLoader cardLoader = CardLoader.GetDeckLoader();

        // load the deck file
        System.out.println("Loading " + m_DeckFile + " ...");



        // convert the lines into cards
        for (String ln: lines ) {
            ProcessLine(ln);
        }

        // create the deck image
        m_Image = new BufferedImage(kWidth, kHeight, BufferedImage.TYPE_INT_RGB);

        Graphics g = m_Image.getGraphics();

        // process the images into one
        int xLoc = 0, yLoc = 0;
        for (int i = 0; i < m_Cards.size(); i++)
        {
            GetCardImage(g, xLoc, yLoc, i);

            // next column
            xLoc++;

            // next row
            if (xLoc > 9)
            {
                xLoc = 0;
                yLoc++;

                // overflow
                if (yLoc > 6)
                {
                    break;
                }
            }
        }

        // save the image
        try {
            ImageIO.write(m_Image, "JPG", new File (m_OutDir, "DeckImage.jpg"));
            System.out.println("Image saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image failed to save");
        }
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
                                Thread.sleep(50);
                                break;
                            case IDLE:
                                if (!m_IsComplete && m_DeckFile != null && m_DeckFile != "")
                                {
                                    m_State = LoadingState.LOADING_FILE;
                                }
                                break;
                            case LOADING_FILE:
                                LoadFile();
                                m_NumberOfLines = m_DeckList.size();
                                m_CurrentLine = 0;
                                m_State = LoadingState.LOADING_CARD_DATA;
                                break;
                            case LOADING_CARD_DATA:
                                if (m_CurrentLine < m_NumberOfLines) {
                                    ProcessLine(m_DeckList.get(m_CurrentLine));
                                    m_CurrentLine++;
                                }
                                else {
                                    m_State = LoadingState.LOADING_CARD_IMAGES;
                                }
                                break;
                            case LOADING_CARD_IMAGES:
//TODO
                            case SAVING_IMAGE:
//TODO
                            case SAVING_DECK:
//TODO
                            case CLEAN_UP:
                                //TODO m_Task = new Deck(m_DeckFile, m_OutDir);
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
        m_IsComplete = false;
        m_State = LoadingState.IDLE;

        if (t == null) {
            t = new Thread(this);
            t.start();
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
    }

    public void AddCard (Integer a_Count, String a_Name)
    {
        S_DeckCard dc = new S_DeckCard();
        dc.card = CardLoader.LoadCard(a_Name);
        dc.count = a_Count;

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
