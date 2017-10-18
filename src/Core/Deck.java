package Core;

import org.apache.commons.lang3.math.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Deck {

    private int kWidth = 223*10;
    private int kHeight = 311*7;

    public class S_DeckCard {
        public MTGCard card;
        public Integer count;
    }

    private Vector<S_DeckCard> m_Cards;
    private int m_DeckSize;
    private BufferedImage m_Image;

    //private CardLoader m_DeckLoader;

    public Deck(String a_DeckFile) {

        m_Cards = new Vector<S_DeckCard>();
        m_DeckSize = 0;

        // load the card database
        CardLoader cardLoader = CardLoader.GetDeckLoader();

        // load the deck file
        System.out.println("Loading " + a_DeckFile + " ...");

        // load lines from file
        List<String > lines = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(a_DeckFile));
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }

            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Read exception occurred with %s.", a_DeckFile);
            e.printStackTrace();;
        }

        // convert the lines into cards
        for (String ln: lines ) {
            ProcessLine(ln);
        }

        // find all cards in deck

        // create the deck image
        m_Image = new BufferedImage(kWidth, kHeight, BufferedImage.TYPE_INT_RGB);

        Graphics g = m_Image.getGraphics();

        // process the images into one
        int xLoc = 0, yLoc = 0;
        for (int i = 0; i < m_Cards.size(); i++)
        {
            //GetCardImage(g, xLoc, yLoc, i);

            // next column
            xLoc++;

            // next row
            if (xLoc > 9)
            {
                xLoc = 1;
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
            ImageIO.write(m_Image, "JPG", new File ("res/", "deck.jpg"));
            System.out.println("Image saved");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image failed to save");
        }
    }

    private void GetCardImage(Graphics g, int xLoc, int yLoc, int id)
    {
        try {
            //File f = new File(m_Cards.elementAt(id).card.getImageUrl());

            System.out.println("Drawing image for " + m_Cards.elementAt(id).card.getName());
            //URL url = new URL (m_Cards.elementAt(id).card.);
            //BufferedImage cardImage = ImageIO.read(url);

            //g.drawImage(cardImage, xLoc * kWidth, yLoc * kHeight, null);
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
