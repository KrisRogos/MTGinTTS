package Core;

public class MTGSet {
    String name;
    String code;
    String releaseDate;
    MTGCard cards[];


    @Override
    public String toString() {
        String ret = name + "(" + code + ") was released on " + releaseDate + " with " + cards.length + " cards:";

        for (int i = 0; i < cards.length; i++) {
            ret += "\n" + cards[i].getName();
        }

        return ret;
    }
}
