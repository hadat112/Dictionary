import javafx.scene.Parent;

import java.io.IOException;

public class Controller {
    private WordsView wordsView;
    private WordList wordList;
    private DefView defView;

    public void init(Parent root) {
        wordList = new WordList();
        wordsView = new WordsView(root);
        defView = new DefView(root);
    }


    public void initData() throws IOException {
        wordList.createWordList();
        wordsView.displayWordList();
        wordsView.loadDef(defView);
//      history.loadHistory();
    }
}
