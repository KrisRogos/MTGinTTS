package Core;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class MTGSet implements Comparator<MTGSet>, Comparable<MTGSet> {
    String name;
    String code;
    Date releaseDate;
    String mkm_id;
    MTGCard cards[];


    @Override
    public String toString() {
        String ret = name + " (" + mkm_id + " " + code + ") was released on " +
                new SimpleDateFormat("d 'day of' MMM yyyy").format(releaseDate) +
                " with " + cards.length + " cards:\n";

        /*for (int i = 0; i < cards.length; i++) {
            ret += "\n" + cards[i].getName();
        }*/

        return ret;
    }

    @Override
    public int compare(MTGSet o1, MTGSet o2) throws NullPointerException, ClassCastException {
        return  (o2.releaseDate.compareTo(o1.releaseDate));
    }

    @Override
    public int compareTo(MTGSet o) throws NullPointerException, ClassCastException {
        return o.releaseDate.compareTo(this.releaseDate);
    }

    public void Sort() {
        Arrays.sort(cards);
    }


    public MTGCard Find(String name) {
        int start = 0, end = cards.length - 1;

        while (start <= end) {
            int pivot = (start + end) / 2;
            int result = cards[pivot].getName().compareToIgnoreCase(name);

            // the card is earlier
            if (result < 0) {
                start = pivot + 1;
            }
            // the card is after this
            else if (result > 0) {
                end = pivot - 1;
            }
            // this is the card
            else if (result == 0) {
                return cards[pivot];
            }

        }

        // card not found
        return null;
    }


}
