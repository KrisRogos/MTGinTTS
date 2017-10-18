package Core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MTGSet {
    String name;
    String code;
    Date releaseDate;
    String mkm_id;
    MTGCard cards[];


    @Override
    public String toString() {
        String ret = name + " (" + mkm_id + " " + code + ") was released on " + new SimpleDateFormat("d 'day of' MMM yyyy").format(releaseDate) + " with " + cards.length + " cards:\n";

        /*for (int i = 0; i < cards.length; i++) {
            ret += "\n" + cards[i].getName();
        }*/

        return ret;
    }
}
