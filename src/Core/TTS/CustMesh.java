package Core.TTS;

import java.awt.*;

public class CustMesh {
    private String  MeshURL;
    private String  DiffuseURL;
    private String  NormalURL;
    private String  ColliderURL;
    private boolean Convex;
    private int     MaterialIndex;
    private int     TypeIndex;
    private boolean CastShadows;

    public CustMesh(String a_Type) {
        if (a_Type.equals("DeckBox")) {
            MeshURL = "https://www.dropbox.com/s/fbpp2c2mzvdi2lg/DeckBox.obj?dl=1";
            DiffuseURL = "https://www.dropbox.com/s/6me42djeef86jem/DeckBox_Black.jpg?dl=1";
            NormalURL = "";
            ColliderURL = "";
            Convex = true;
            MaterialIndex = 1;
            TypeIndex = 6;
            CastShadows = true;
        }
    }
}
