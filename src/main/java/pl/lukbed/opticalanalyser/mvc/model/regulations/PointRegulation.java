package pl.lukbed.opticalanalyser.mvc.model.regulations;

import pl.lukbed.opticalanalyser.mvc.model.exceptions.InvalidInputException;
import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Point;

public class PointRegulation {
    private double horizontal;
    private double vertival;
    private double minimumValue;
    private double maximumValue;

    public PointRegulation(double horizontal, double vertival, double minimumValue, double maximumValue) {
        this.horizontal = horizontal;
        this.vertival = vertival;
        this.minimumValue = Math.abs(minimumValue);
        this.maximumValue = Math.abs(maximumValue);
    }

    public double getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    public double getVertival() {
        return vertival;
    }

    public void setVertival(double vertival) {
        this.vertival = vertival;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public double getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(double maximumValue) {
        this.maximumValue = maximumValue;
    }

    @Override
    public String toString() {
        return "H " + horizontal + " V " + vertival + ": min. " + minimumValue + " max. " + maximumValue + " Cd";
    }

    public PhotometryMessage checkPoint(Point point) throws InvalidInputException {
        if (point.getHorizontal() != horizontal && point.getVertical() != vertival) {
            throw new InvalidInputException("Point not found");
        }

        double value = point.getValue();
        value = Math.round(value*10)/10;

        double minPercent = Math.round(100*value/minimumValue);

        String message = toString() + ";\t\t\tmeasured: " + value + " Cd (" + minPercent + " % of ECE);\t\t\t";

        if (value >= minimumValue && value <= maximumValue) {
            message += "ECE OK";
            return new PhotometryMessage(message, PhotometryStatus.ECE_OK);
        }

        message += "ECE NOK";
        return new PhotometryMessage(message, PhotometryStatus.ECE_NOK);
    }
}
