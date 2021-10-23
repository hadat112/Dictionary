public class Word {
    private String word;
    private String defination;
    private boolean isFavorite = false;

    public Word() {
    }

    public Word(String word, String def) {
        this.word = word;
        this.defination = def;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDef() {
        return defination;
    }

    public void setDef(String def) {
        this.defination = def;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public void unsetFavorite() {
        isFavorite = false;
    }
}
