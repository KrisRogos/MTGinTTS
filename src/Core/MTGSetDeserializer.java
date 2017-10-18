package Core;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MTGSetDeserializer implements JsonDeserializer<MTGSetContainer> {

    @Override
    public MTGSetContainer deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = element.getAsJsonObject();
        List<MTGSet> sets = new ArrayList<MTGSet>();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            // deserialize individual Magic Sets:
            MTGSet set = context.deserialize(entry.getValue(), MTGSet.class);
            sets.add(set);
        }

        return new MTGSetContainer(sets);
    }
}
