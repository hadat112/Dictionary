import javafx.scene.Parent;

import java.io.IOException;

public class Controller {
    private WordsView wordsView;
    private WordList wordList;
    private DefView defView;
    private AddingWord addingWord;
    private DeletingWord deletingWord;
    private FixingWord fixingWord;
    private SearchingWord searchingWord;

    public void init(Parent root) {
        wordList = new WordList();
        wordsView = new WordsView(root);
        defView = new DefView(root);
        addingWord = new AddingWord(root);
        deletingWord = new DeletingWord(root);
        fixingWord = new FixingWord(root);
        searchingWord = new SearchingWord(root);
    }


    public void initData() throws IOException {
        wordList.createWordList();
        wordsView.displayWordList();
//      history.loadHistory();
    }

    public void function() {
        wordsView.loadDef(defView);

        addingWord.addEvent(wordsView);
        deletingWord.delEvent(wordsView, defView);
        fixingWord.fixEvent(wordsView, defView);
    }
}
