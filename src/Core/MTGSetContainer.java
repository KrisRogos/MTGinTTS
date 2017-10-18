package Core;

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
}
