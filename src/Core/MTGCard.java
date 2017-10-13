package Core;

import java.awt.image.BufferedImage;

public class MTGCard {

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

    private String m_Name;
    private String m_Set;
    private String m_Description;
    private String m_ManaCost;
    private String m_SubType;
    private String m_Artist;
    private Integer m_CMC;
    private Integer m_UID;
    private Integer m_Number;
    private E_CardTypes m_Type;
    private E_Colors[] m_Colors;
    private boolean m_IsPermament;
    private boolean m_IsLegendary;
    private BufferedImage m_Image;

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public String getM_Set() {
        return m_Set;
    }

    public void setM_Set(String m_Set) {
        this.m_Set = m_Set;
    }

    public String getM_Description() {
        return m_Description;
    }

    public void setM_Description(String m_Description) {
        this.m_Description = m_Description;
    }

    public String getM_ManaCost() {
        return m_ManaCost;
    }

    public void setM_ManaCost(String m_ManaCost) {
        this.m_ManaCost = m_ManaCost;
    }

    public String getM_SubType() {
        return m_SubType;
    }

    public void setM_SubType(String m_SubType) {
        this.m_SubType = m_SubType;
    }

    public String getM_Artist() {
        return m_Artist;
    }

    public void setM_Artist(String m_Artist) {
        this.m_Artist = m_Artist;
    }

    public Integer getM_CMC() {
        return m_CMC;
    }

    public void setM_CMC(Integer m_CMC) {
        this.m_CMC = m_CMC;
    }

    public Integer getM_UID() {
        return m_UID;
    }

    public void setM_UID(Integer m_UID) {
        this.m_UID = m_UID;
    }

    public Integer getM_Number() {
        return m_Number;
    }

    public void setM_Number(Integer m_Number) {
        this.m_Number = m_Number;
    }

    public E_CardTypes getM_Type() {
        return m_Type;
    }

    public void setM_Type(E_CardTypes m_Type) {
        this.m_Type = m_Type;
    }

    public E_Colors[] getM_Colors() {
        return m_Colors;
    }

    public void setM_Colors(E_Colors[] m_Colors) {
        this.m_Colors = m_Colors;
    }

    public boolean isM_IsPermament() {
        return m_IsPermament;
    }

    public void setM_IsPermament(boolean m_IsPermament) {
        this.m_IsPermament = m_IsPermament;
    }

    public boolean isM_IsLegendary() {
        return m_IsLegendary;
    }

    public void setM_IsLegendary(boolean m_IsLegendary) {
        this.m_IsLegendary = m_IsLegendary;
    }

    public BufferedImage getM_Image() {
        return m_Image;
    }

    public void setM_Image(BufferedImage m_Image) {
        this.m_Image = m_Image;
    }

    public void Load(String a_Name)
    {
        // todo
    }

    public void Load(String a_Name, String a_Set)
    {
        // todo
    }

}
