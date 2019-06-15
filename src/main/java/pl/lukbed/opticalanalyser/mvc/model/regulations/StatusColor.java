package pl.lukbed.opticalanalyser.mvc.model.regulations;

public enum StatusColor {
    DEFAULT("black"),
    RED("red"),
    YELLOW("yellow"),
    GREEN("green");

    private String colorName;

    StatusColor(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return colorName;
    }
}
