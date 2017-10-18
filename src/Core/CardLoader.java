package Core;

import com.google.gson.FieldNamingPolicy;
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
    private static MTGSetContainer k_Sets;


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
        ParseCardTable();

//        System.out.println(k_Sets.toString());
    }

    private void ParseCardTable() {
        // create a json builder
        GsonBuilder builder = new GsonBuilder();

        // make sure custom deserialization is used
        builder.registerTypeAdapter(MTGSetContainer.class, new MTGSetDeserializer());

        // parse the json file using a custom date format
        Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
        k_Sets = gson.fromJson(k_CardTable, MTGSetContainer.class);

        // sort the sets
        k_Sets.SortAll();
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
        return k_Sets.Find(name);
    }

}
