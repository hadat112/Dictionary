import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class AddingWord {
    private Button addWordBtn;
    private final String addID = "#add";

    public AddingWord(Parent root) {
        this.addWordBtn = (Button) root.lookup(addID);
    }

    public void addEvent(WordsView wordsView, SearchingWord searchingWord) {
        addWordBtn.setOnMouseClicked(e -> {
            searchingWord.hideSuggestion();
            showInputForm(wordsView);
        });
    }

    /**
     * Hiển thị bảng nhập từ mới.
     * @param wordsView danh sách từ.
     */
    private void showInputForm(WordsView wordsView) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add a word to dictionary");

        Label label = new Label("New word:");
        Label label1 = new Label("Defination:");
        TextField newWordField = new TextField();
        TextArea newDefField = new TextArea();

        GridPane grid = new GridPane();
        grid.add(label, 1, 1);
        grid.add(label1, 1, 3);
        grid.add(newWordField, 1, 2);
        grid.add(newDefField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Button okBtn = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okBtn.addEventFilter(
                ActionEvent.ACTION, event -> {
                    String word = newWordField.getText();
                    String def = "<html><i>" + word + "</i><br/>" + "<ul><li><font color='#cc0000'><b>"
                            + newDefField.getText() + "</b></font></li></ul></html>";
                    if (checkWord(word)) {
                        showWarning(word);
                        event.consume();
                    } else {
                        WordList.getWordList().put(word, new Word(word, def));
                        wordsView.displayWordList();
                        wordsView.setChange();
                    }
                }
        );

        dialog.showAndWait();
    }

    /**
     * Kiểm tra từ trong danh sách.
     * @param word từ cần ktra
     * @return boolean
     */
    private boolean checkWord(String word) {
        return WordList.getWordList().containsKey(word);
    }

    /**
     * Cảnh báo từ đã suất hiện.
     * @param word Từ cần thêm
     */
    public void showWarning(String word) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Không thể thêm từ !!!");
        alert.setContentText("Từ: " + word + "\" đã tồn tại!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.hide();
        }
    }


}
