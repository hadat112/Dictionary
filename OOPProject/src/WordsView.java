import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Map;

public class WordsView {
    private final String wvid = "#wordView";
    private ListView<String> wordsView;
    private WordList wordList;

    public WordsView(Parent root) {
        wordsView = (ListView<String>) root.lookup(wvid);
    }

    public void displayWordList() {
        wordsView.getItems().clear();
        wordsView.getItems().addAll(wordList.getWordList().keySet());
    }

    public void loadDef(DefView defView) {
        wordsView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
//                    findField.hideSearchView();
                    String selectedWordDef = "";
                    if(newValue != null){
                        selectedWordDef = wordList.getWordList().get(newValue).getDef();
                    }
                    defView.displayDef(selectedWordDef);
//                    setCurrent(newValue);
                }
        );
    }
}
