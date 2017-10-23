package Core.TTS;

public class SavedObject {
    private String SaveName;
    private String GameMode;
    private String Date;
    private String Table;
    private String Sky;
    private String Note;
    private String Rules;
    private String LuaScript;
    private String LuaScriptState;
    private ObjectState ObjectStates[];
    private String TabStates;
    private String VersionNumber;

    public String getSaveName() {
        return SaveName;
    }

    public void setSaveName(String saveName) {
        SaveName = saveName;
    }

    public String getGameMode() {
        return GameMode;
    }

    public void setGameMode(String gameMode) {
        GameMode = gameMode;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String table) {
        Table = table;
    }

    public String getSky() {
        return Sky;
    }

    public void setSky(String sky) {
        Sky = sky;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getRules() {
        return Rules;
    }

    public void setRules(String rules) {
        Rules = rules;
    }

    public String getLuaScript() {
        return LuaScript;
    }

    public void setLuaScript(String luaScript) {
        LuaScript = luaScript;
    }

    public String getLuaScriptState() {
        return LuaScriptState;
    }

    public void setLuaScriptState(String luaScriptState) {
        LuaScriptState = luaScriptState;
    }

    public ObjectState[] getObjectStates() {
        return ObjectStates;
    }

    public void setObjectStates(ObjectState[] objectStates) {
        ObjectStates = objectStates;
    }

    public String getTabStates() {
        return TabStates;
    }

    public void setTabStates(String tabStates) {
        TabStates = tabStates;
    }

    public String getVersionNumber() {
        return VersionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        VersionNumber = versionNumber;
    }
}
