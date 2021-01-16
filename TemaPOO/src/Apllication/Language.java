package Apllication;

public class Language {
    String nameOfLanguage;
    String level;

    public Language(String nameOfLanguage, String level) {
        this.nameOfLanguage = nameOfLanguage;
        this.level = level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setNameOfLanguage(String nameOfLanguage) {
        this.nameOfLanguage = nameOfLanguage;
    }

    public String getNameOfLanguage() {
        return nameOfLanguage;
    }

    @Override
    public String toString() {
        return "{" + nameOfLanguage + "-" + level + '}';
    }
}
