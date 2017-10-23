package Core.TTS;

public class CustDeck {
    private String FaceURL;
    private String BackURL;
    private int NumWidth;
    private int NumHeight;
    private boolean BackIsHidden;
    private boolean UniqueBack;

    public String getFaceURL() {
        return FaceURL;
    }

    public void setFaceURL(String faceURL) {
        FaceURL = faceURL;
    }

    public String getBackURL() {
        return BackURL;
    }

    public void setBackURL(String backURL) {
        BackURL = backURL;
    }

    public int getNumWidth() {
        return NumWidth;
    }

    public void setNumWidth(int numWidth) {
        NumWidth = numWidth;
    }

    public int getNumHeight() {
        return NumHeight;
    }

    public void setNumHeight(int numHeight) {
        NumHeight = numHeight;
    }

    public boolean isBackIsHidden() {
        return BackIsHidden;
    }

    public void setBackIsHidden(boolean backIsHidden) {
        BackIsHidden = backIsHidden;
    }

    public boolean isUniqueBack() {
        return UniqueBack;
    }

    public void setUniqueBack(boolean uniqueBack) {
        UniqueBack = uniqueBack;
    }
}
