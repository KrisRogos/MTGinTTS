package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class DeckLoader {

    private static final String FILENAME = "res/AllSets.json";
    //private static final File file = new File(DeckLoader.class.getClass().getResource(FILENAME).getFile());
    private static final InputStream stream = DeckLoader.class.getClass().getResourceAsStream(FILENAME);

    // read in the json file as stream
    public static MTGCard LoadCard(String name)
    {
        try {
            InputStream st = new FileInputStream(FILENAME);

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
        }

        return null;
    }

}
