package Core.TTS;

public class ObjectState {
    private String Name;
    private Transformation Transform;
    private String Nickname;
    private String Description;
    private Colour ColorDiffuse;
    private boolean Locked;
    private boolean Grid;
    private boolean Snap;
    private boolean Autoraise;
    private boolean Sticky;
    private boolean Tooltip;
    private boolean GridProjectoin;
    private boolean Hands;
    private int CardID;
    private boolean SidewaysCard;
    private CustDeck CustomDeck;
    private String LuaScript;
    private String LuaScripts;
    private String GUID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Transformation getTransform() {
        return Transform;
    }

    public void setTransform(Transformation transform) {
        Transform = transform;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Colour getColorDiffuse() {
        return ColorDiffuse;
    }

    public void setColorDiffuse(Colour colorDiffuse) {
        ColorDiffuse = colorDiffuse;
    }

    public boolean isLocked() {
        return Locked;
    }

    public void setLocked(boolean locked) {
        Locked = locked;
    }

    public boolean isGrid() {
        return Grid;
    }

    public void setGrid(boolean grid) {
        Grid = grid;
    }

    public boolean isSnap() {
        return Snap;
    }

    public void setSnap(boolean snap) {
        Snap = snap;
    }

    public boolean isAutoraise() {
        return Autoraise;
    }

    public void setAutoraise(boolean autoraise) {
        Autoraise = autoraise;
    }

    public boolean isSticky() {
        return Sticky;
    }

    public void setSticky(boolean sticky) {
        Sticky = sticky;
    }

    public boolean isTooltip() {
        return Tooltip;
    }

    public void setTooltip(boolean tooltip) {
        Tooltip = tooltip;
    }

    public boolean isGridProjectoin() {
        return GridProjectoin;
    }

    public void setGridProjectoin(boolean gridProjectoin) {
        GridProjectoin = gridProjectoin;
    }

    public boolean isHands() {
        return Hands;
    }

    public void setHands(boolean hands) {
        Hands = hands;
    }

    public int getCardID() {
        return CardID;
    }

    public void setCardID(int cardID) {
        CardID = cardID;
    }

    public boolean isSidewaysCard() {
        return SidewaysCard;
    }

    public void setSidewaysCard(boolean sidewaysCard) {
        SidewaysCard = sidewaysCard;
    }

    public CustDeck getCustomDeck() {
        return CustomDeck;
    }

    public void setCustomDeck(CustDeck customDeck) {
        CustomDeck = customDeck;
    }

    public String getLuaScript() {
        return LuaScript;
    }

    public void setLuaScript(String luaScript) {
        LuaScript = luaScript;
    }

    public String getLuaScripts() {
        return LuaScripts;
    }

    public void setLuaScripts(String luaScripts) {
        LuaScripts = luaScripts;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }
}
