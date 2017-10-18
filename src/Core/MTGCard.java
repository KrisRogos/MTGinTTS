package Core;

import java.awt.image.BufferedImage;
import java.util.Comparator;

public class MTGCard implements Comparator<MTGCard>, Comparable<Object> {

    @Override
    public int compare(MTGCard o1, MTGCard o2) throws NullPointerException, ClassCastException {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }

    @Override
    public int compareTo(Object o) throws NullPointerException, ClassCastException  {
            return this.getName().compareToIgnoreCase(o.toString());
    }

    @Override
    public String toString() {
        return name;
    }

    public static int compareTo(MTGCard o1, String o2) throws NullPointerException, ClassCastException  {
        return o1.getName().compareToIgnoreCase(o2);
    }


    public enum  E_CardTypes {
        Land,
        Creature,
        Artifact,
        Enchantment,
        Planeswalker,
        Instant,
        Sorcery
    }

    public enum E_Colors {
        White,
        Blue,
        Black,
        Red,
        Green,
        Multi,
        Colorless
    }

    private String artist;
    private int cmc;
    private String colorIdentity[];
    private String flavor;
    private String id;
    private String layout;
    private String manaCost;
    private String mciNumber;
    private int multiverseid;
    private String name;
    private String number;
    private String power;
    private String rarity;
    private String[] subtypes;
    private String text;
    private String toughness;
    private int loyalty;
    private String type;
    private String[] types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MTGCard mtgCard = (MTGCard) o;

        return name != null ? name.equals(mtgCard.name) : mtgCard.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getCmc() {
        return cmc;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public String[] getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(String[] colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String getFlavour() {
        return flavor;
    }

    public void setFlavour(String flavour) {
        this.flavor = flavour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getMciNumber() {
        return mciNumber;
    }

    public void setMciNumber(String mciNumber) {
        this.mciNumber = mciNumber;
    }

    public int getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(int multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String[] getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String[] subtypes) {
        this.subtypes = subtypes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
