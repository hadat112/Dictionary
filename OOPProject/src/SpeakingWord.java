import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class SpeakingWord {
    private Button speakBtn;
    private final String speakID = "#speak";

    private static VoiceManager voiceManager = VoiceManager.getInstance();
    private static Voice speaker;

    static {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        speaker = VoiceManager.getInstance().getVoice("kevin16");
        speaker.allocate();
    }

    public SpeakingWord(Parent root) {
        speakBtn = (Button) root.lookup(speakID);
    }

    /**
     * Nói khi bấm nút
     * @param wordsView Dsach từ
     * @param searchingWord Bảng tìm kiếm
     */
    public void speak(WordsView wordsView, SearchingWord searchingWord) {
        speakBtn.setOnMouseClicked(e -> {
            searchingWord.hideSuggestion();
            String word = wordsView.getCurrent();
            speaker.speak(word);
        });
    }

}
