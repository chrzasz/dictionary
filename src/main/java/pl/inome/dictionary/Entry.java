package pl.inome.dictionary;

class Entry {

    private String original;
    private String translation;

    Entry() {
    }

    Entry(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }

    String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return original + ";" + translation;
    }
}
