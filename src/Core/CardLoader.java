package Core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import java.io.*;

public class CardLoader {

    /*private static final String FILENAME = "res/AllSets.json";
    //private static final File file = new File(CardLoader.class.getClass().getResource(FILENAME).getFile());
    private static final InputStream stream = CardLoader.class.getClass().getResourceAsStream(FILENAME);*/

    private static String k_CardTable;
    private static final String k_FileName = "res/AllSets.json";
    private static CardLoader k_CardLoader;


    public static CardLoader GetDeckLoader()
    {
        if (k_CardLoader == null) {
            // create the deck loader
            k_CardLoader = new CardLoader();
        }

        return k_CardLoader;
    }

    private CardLoader() {
        LoadFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type mapType = new TypeToken<Collection<Map<String, MTGSet>>>() {}.getType();
        Object map = gson.fromJson(k_CardTable, Object.class);

        System.out.println("hi");

        //MTGSetContainer sets = gson.fromJson(k_CardTable, MTGSetContainer.class);

        //gson.

        //System.out.println(sets.toString());

        //System.out.println(sets.length);

       /* for (int i = 0; i < sets.length; i++)
            System.out.println(sets[i].toString());*/
    }

    private void LoadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(k_FileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            // load in line by line
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            k_CardTable = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // read in the json file as stream
    public static MTGCard LoadCard(String name)
    {




        /*try {
            InputStream st = new FileInputStream(fName);

            JsonReader reader = new JsonReader(new InputStreamReader(st, "UTF-8"));
            Gson gson = new GsonBuilder().create();

            // read in
            reader.beginArray();
            while (reader.hasNext())
            {
                // read a block of data as object
                MTGCard card = gson.fromJson(reader, MTGCard.class);

                if (card.getName() == name)
                {
                    System.out.println("I found it!");
                    return card;
                }
                break;
            }
            reader.close();

        } catch (Exception e) {
            System.err.format("Error occurred while looking for a card named %s.", name);
            e.printStackTrace();;
        }*/

        return null;
    }

}
