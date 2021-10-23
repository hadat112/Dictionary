import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleSearching {
    private final Button googleSearchBtn;
    private final String ggSearchID = "#googleSearch";

    public GoogleSearching(Parent root){
        googleSearchBtn = (Button) root.lookup(ggSearchID);
    }

    /**
     * dịch google khi bấm nút dịch.
     * @param wordsView danh sách từ
     * @param searchingWord từ tìm kiếm
     * @param defView nghĩa
     */
    public void translateFromGG(WordsView wordsView, SearchingWord searchingWord, DefView defView) {
        googleSearchBtn.setOnAction(e -> {
            searchingWord.hideSuggestion();
            wordsView.setCurrent(searchingWord.getSearchText());
            if(wordsView.getCurrent()!=null){
                try {
                    String def = "<b> -" + APItranslate(wordsView.getCurrent()) + "</b>";
                    defView.displayDef(def);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    /**
     * API translate.
     * @param text từ cần dịch
     * @return nghĩa
     * @throws IOException
     */
    public static String APItranslate(String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbxueKM8M8EMiiQYoYEmTZZR3L-TQ61S65DVeNvUo5DaxWbFUgw5/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + "vi" +
                "&source=" + "en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
        conection.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
