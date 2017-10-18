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
        for (MTGSet set : mtgSets) {
            res += set.toString();
        }

        return res;
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
            return set.Find("name");
        }

        return null;
    }
}
