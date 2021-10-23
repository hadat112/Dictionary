import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class FixingWord {
    private final String fixID = "#fix";
    private final Button fixWordBtn;

    public FixingWord(Parent root) {
        this.fixWordBtn = (Button) root.lookup(fixID);
    }

    public void fixEvent(WordsView wordsView, DefView defView) {
        fixWordBtn.setOnMouseClicked(e -> {
            showInputForm(wordsView, defView);
        });
    }

    private void showInputForm(WordsView wordsView, DefView defView) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Fix word's defination");

        Label label = new Label("Sửa nghĩa của từ: " + wordsView.getCurrent());
        Label label1 = new Label("Nghĩa mới: ");
        TextArea textField1 = new TextArea();

        GridPane grid = new GridPane();
        grid.add(label, 1, 1);
        grid.add(label1, 1, 2);
        grid.add(textField1, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Button okBtn = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okBtn.addEventFilter(
                ActionEvent.ACTION, event -> {
                    String word = wordsView.getCurrent();
                    String def = "<html><i>" + word + "</i><br/>" + "<ul><li><font color='#cc0000'><b>"
                            + textField1.getText() + "</b></font></li></ul></html>";
                    WordList.getWordList().put(word, new Word(word, def));
                    wordsView.displayWordList();
                    defView.displayDef(def);
                    wordsView.setCurrent(word);
                    wordsView.setChange();
                }
        );

        dialog.showAndWait();
    }
}
