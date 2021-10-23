import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SearchingWord {
    private Button searchBtn;
    private final String searchID = "#search";

    private TextField searchField;
    private final String searchFieldID = "#textField";

    private final ListView<String> suggestion;
    private final String suggestionID = "#suggestion";

    private List<String> suggestList = new ArrayList<>();

    public SearchingWord(Parent root) {
        searchBtn = (Button) root.lookup(searchID);
        searchField = (TextField) root.lookup(searchFieldID);
        suggestion = (ListView<String>) root.lookup(suggestionID);
        hideSuggestion();
    }

    private void showSuggestion() {
        suggestion.setVisible(true);
    }

    private void hideSuggestion() {
        suggestion.setVisible(false);
    }

    public void setMouseEventToSearchView() {
        searchField.setOnMouseClicked(e -> {
            showSuggestion();
            if(getSearchText().equals("")){
//                loadHistory();
            }
        });
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public void suggest(DefView defView, WordsView wordsView) {
        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.equals("")) {
                        addToSuggest(newValue);
                        loadSuggest();
                        loadSearchViewList(defView, wordViewList);
                        showSearchView();
                    } else {
                        searchList.clear();
                        loadHistory();
                    }
                }
        );
    }

    private void addToSuggest(String word) {
        suggestList.clear();
        for (String key : WordList.getWordList().keySet()) {
            if (key.startsWith(word)) {
                suggestList.add(key);
            }
        }
    }

    private void loadSuggest() {
        suggestion.getItems().clear();
        suggestion.getItems().addAll(suggestList);
    }

    public void loadSearchViewList(DefView defView, WordViewList wordViewList) {
        searchView.setOnMouseClicked(e -> {
            String temp = searchView.getSelectionModel().getSelectedItem();
            addToHistorySearch(temp);
            wordViewList.jumpTo(temp);
            setCurrent(temp);
            String def = findDef(getCurrent());
            findTextField.setText(getCurrent());
            defView.representDef(def);
            searchView.setVisible(false);
        });
    }

    public void showWordSearch(DefView defView, WordsView wordsView) {
        searchBtn.setOnAction(e -> {
            hideSuggestion();
            String word = getSearchText();
            wordsView.setCurrent(word);
//            addToHistorySearch(word);
            wordsView.jumpTo(word);
            WordList.getDef(word);
            String def = (wordsView.getCurrent());
            defView.displayDef(def);
        });
    }
}
