package pl.lukbed.opticalanalyser.mvc.model.photometricGrid;

public class Point {
    private double horizontal;
    private double vertical;
    private double value;

    public Point(double horizontal, double vertical, double value) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.value = value;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public void setVertical(double vertical) {
        this.vertical = vertical;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "H " + horizontal + " V " + vertical + ": " + value + " Cd";
    }
}
