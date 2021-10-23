import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Map;

public class WordsView {
    private final String wvid = "#wordView";
    private ListView<String> wordsView;
    private WordList wordList;
    private boolean change = false;
    private static String current = "";

    public WordsView(Parent root) {
        wordsView = (ListView<String>) root.lookup(wvid);
    }

    public boolean isChange() {
        return change;
    }

    public void setChange() {
        this.change = true;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCurrent(){
        return current;
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
                    current = newValue;
                }
        );
    }

    public void jumpTo(String word){
        wordsView.scrollTo(word);
    }
}
