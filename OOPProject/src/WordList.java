import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

public class WordList {
    private final String splitString = "<html>";
    private static Map<String, Word> wordList = new TreeMap<>();
    private final String fileE_V = "./file/E_V.txt";

    public static Map<String, Word> getWordList() {
        return wordList;
    }

    public String getDef(String word) {
        if(wordList.containsKey(word)){
            return wordList.get(word).getDef();
        }
        return null;
    }

    /**
     * tạo ra danh sách từ file text
     */
    public void createWordList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileE_V));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(splitString);
            parts[1] = "<html>" + parts[1];
            wordList.put(parts[0], new Word(parts[0], parts[1]));
        }
    }

    /**
     * Lưu vao file
     */
    public void exportToFile() {
        try {
            Formatter fileOut = new Formatter(fileE_V);
            for (Map.Entry<String, Word> entry : wordList.entrySet()) {
                fileOut.format("%s%s%s", entry.getKey()
                        , entry.getValue().getDef(), "\r\n");
            }
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }


    public void loadHistory() {

    }
}
