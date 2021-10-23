import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DeletingWord {
    private Button delBtn;
    private final String delID = "#delete";

    public DeletingWord(Parent root) {
        this.delBtn = (Button) root.lookup(delID);
    }

    /**
     * Xoá từ khi bấm nút xoá
     * @param wordsView dsach
     * @param defView nghĩa
     * @param searchingWord bảng tìm kiém.
     */
    public void delEvent(WordsView wordsView, DefView defView, SearchingWord searchingWord) {
        delBtn.setOnMouseClicked(e -> {
            searchingWord.hideSuggestion();
            if(!wordsView.getCurrent().equals("") || wordsView.getCurrent() != null){
                showAlert(wordsView, defView);
            }
        });
    }

    /**
     * Thông báo xoá.
     * @param wordsView Dsach từ
     * @param defView Nghĩa
     */
    private void showAlert(WordsView wordsView, DefView defView) {
        Alert dltAlert = new Alert(Alert.AlertType.CONFIRMATION);
        dltAlert.setTitle("Delete word");
        dltAlert.setHeaderText("Bạn có muốn xoá \"" + wordsView.getCurrent() + "\"?");
        Optional<ButtonType> option = dltAlert.showAndWait();

        if (option.get() == ButtonType.OK) {
            removeChoose(wordsView);
            wordsView.displayWordList();
            defView.clearDefView();
            wordsView.setChange();
        }
    }

    /**
     * Bỏ chọn từ
     * @param wordsView Dsach từ
     */
    private void removeChoose(WordsView wordsView){
        WordList.getWordList().remove(wordsView.getCurrent());
        wordsView.setCurrent("");
    }
}
