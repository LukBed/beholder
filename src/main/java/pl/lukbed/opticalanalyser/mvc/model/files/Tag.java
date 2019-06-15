package pl.lukbed.opticalanalyser.mvc.model.files;

public class Tag {
    private String name;
    private String describtion;

    public Tag(String name, String describtion) {
        this.name = name;
        this.describtion = describtion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    @Override
    public String toString() {
        return "[" + name + "] " + describtion;
    }
}
