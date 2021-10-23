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
    private GoogleSearching googleSearching;
    private SpeakingWord speakingWord;

    public void init(Parent root) {
        wordList = new WordList();
        wordsView = new WordsView(root);
        defView = new DefView(root);
        addingWord = new AddingWord(root);
        deletingWord = new DeletingWord(root);
        fixingWord = new FixingWord(root);
        searchingWord = new SearchingWord(root);
        googleSearching = new GoogleSearching(root);
        speakingWord = new SpeakingWord(root);
    }


    public void initData() throws IOException {
        wordList.createWordList();
        wordsView.displayWordList();
    }

    public void function(Parent root) {
        wordsView.loadDef(defView, searchingWord);

        addingWord.addEvent(wordsView, searchingWord);
        deletingWord.delEvent(wordsView, defView, searchingWord);
        fixingWord.fixEvent(wordsView, defView, searchingWord);

        searchingWord.setMouseEventToSearchView();
        searchingWord.loadSearchViewList(defView, wordsView);
        searchingWord.suggest(defView, wordsView);
        searchingWord.showWordSearch(defView, wordsView);
        googleSearching.translateFromGG(wordsView, searchingWord, defView);
        searchingWord.closeSuggest(root);

        speakingWord.speak(wordsView, searchingWord);
    }
}
