package Apllication;

public class Language {
    private String name;
    private String level;

    public Language(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setName(String nameOfLanguage) {
        this.name = nameOfLanguage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "-" + level;
    }
}
