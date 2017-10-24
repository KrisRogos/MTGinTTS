package Core.TTS;

import java.util.ArrayList;

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
    private boolean GridProjection;
    private boolean Hands;
    private int MaterialIndex;
    private int MeshIndex;
    private CustMesh CustomMesh;
    private int CardID;
    private boolean SidewaysCard;
    private CustDeck CustomDeck;
    private String LuaScript;
    private String LuaScriptState;
    private ArrayList<ObjectState> ContainedObjects;
    private String GUID;

    public ObjectState(ObjectTypes a_Type,String a_Name, String a_Description) {
        switch (a_Type) {
            case Card:
                Name = "Card";

                break;
            case Deck:
                Name = "Deck";
                Nickname = a_Name;
                Description = a_Description;
                Transform = new Transformation(-75.4588547, 5.89332533, 38.6393471,
                        0.000456450158, 180.030136, 0.000136776114,
                        1.0, 1.0, 1.0);
                ColorDiffuse = new Colour(0.713235259, 0.713235259, 0.713235259);
                SidewaysCard = false;
                //DeckIDs;
                break;
            case CustomModelBag:
                Name = "Custom_Model_Bag";
                Nickname = a_Name;
                Description = a_Description;
                Transform = new Transformation(-68.81479, 4.09982443, 27.75198,
                        -5.565192E-05, 180.0053, 4.46738522E-05,
                        1.0, 1.0, 1.0);
                ColorDiffuse = new Colour(0.9999998, 0.9921637, 0.9999998);
                CustomMesh = new CustMesh("DeckBox");
                ContainedObjects = new ArrayList<ObjectState>();

                break;
        }

        Locked = false;
        Grid = true;
        Snap = true;
        Autoraise = true;
        Sticky = true;
        Tooltip = true;
        GridProjection = true;
        Hands = false;
        MaterialIndex = -1;
        MeshIndex = -1;
        LuaScript = "";
        LuaScriptState = "";
        GUID = "aaaaaa";
    }

    public int getMaterialIndex() {
        return MaterialIndex;
    }

    public void setMaterialIndex(int materialIndex) {
        MaterialIndex = materialIndex;
    }

    public int getMeshIndex() {
        return MeshIndex;
    }

    public void setMeshIndex(int meshIndex) {
        MeshIndex = meshIndex;
    }

    public CustMesh getCustomMesh() {
        return CustomMesh;
    }

    public void setCustomMesh(CustMesh customMesh) {
        CustomMesh = customMesh;
    }

    public String getLuaScriptState() {
        return LuaScriptState;
    }

    public void setLuaScriptState(String luaScriptState) {
        LuaScriptState = luaScriptState;
    }

    public ArrayList<ObjectState> getContainedObjects() {
        return ContainedObjects;
    }

    public void setContainedObjects(ArrayList<ObjectState> containedObjects) {
        ContainedObjects = containedObjects;
    }

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

    public boolean isGridProjection() {
        return GridProjection;
    }

    public void setGridProjection(boolean gridProjection) {
        GridProjection = gridProjection;
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

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }
}
