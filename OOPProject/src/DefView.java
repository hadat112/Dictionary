import javafx.scene.Parent;
import javafx.scene.web.WebView;

public class DefView {
    private WebView defView;
    private final String dvID = "#def";

    public DefView(Parent root) {
        defView = (WebView) root.lookup(dvID);
    }

    /**
     * load nghĩa.
     * @param def nghĩa từ
     */
    public void loadDef(String def){
        defView.getEngine().loadContent(def, "text/html");
    }

    /**
     * xoá hết phần nghĩa.
     */
    public void clearDefView() {
        defView.getEngine().loadContent("", "text/html");
    }

    /**
     * hiển thị nghĩa.
     * @param def nghĩa từ.
     */
    public void displayDef(String def) {
        if (def != null) {
            loadDef(def);
        } else {
            clearDefView();
        }
    }
}
