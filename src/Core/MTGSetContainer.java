package Core;

import java.util.*;
import java.util.List;

public class MTGSetContainer {
    List<MTGSet> mtgSets;

    public MTGSetContainer(List<MTGSet> sets) {
        this.mtgSets = sets;
    }

    @Override
    public String toString() {
        String res = "";

        int counter = 0;

        for (MTGSet set : mtgSets) {
            res += set.toString();
            counter += set.cards.length;
        }

        return res + "\n found " + counter + " cards";
    }

    public void Sort() {
        Collections.sort(mtgSets, new MTGSet());
    }


    public void SortAll() {
        Sort();

        for (MTGSet set : mtgSets)
            set.Sort();
    }

    public MTGCard Find(String name)
    {
        for (MTGSet set : mtgSets) {
            MTGCard temp = set.Find(name);
            if (temp != null)
                return temp;
        }

        return null;
    }
}
