package pl.lukbed.opticalanalyser.mvc.model.files;

import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Grid;

import java.util.ArrayList;
import java.util.List;

public class IesFile {
    private String iesna = "IESNA91"; // IESNA91
    private List<Tag> tags = new ArrayList<>(1); // f. ex. [TEST] Project xxx Tail LH T1
    private String tilt = "NONE"; // default TILT = NONE
    // 1 - just 1
    private double initialRaterLumens = -1; // -1 the initial rated lumens or just -1 (default)
    private double multipliyingFactor = 1; // multiplying factor
    private int numberVertical; // number of vertical angles
    private int numberHorizontal; // number of horizontal angles
    // 3 should be just 1
    private int unitsLuminousOpening = 1; // 1 units of dimension of luminous opening, 1 for feet, 2 for meters
    private double widthLuminousOpening = 0; // 0 the width of luminous opening, default 0
    private double lengthLuminousOpening = 0; // 0 the length of luminous opening, default 0
    private double heightLuminousOpening = 0; // 0 the height of luminous opening, default 0
    // 1 just 1
    // 1 just 1
    // 0 just 0
    private double[] horizontalAngles;
    private double[] verticalAngles;
    private double[] values;

    public IesFile(String iesna, List<Tag> tags, String tilt, double initialRaterLumens, double multipliyingFactor,
                   int numberVertical, int numberHorizontal, int unitsLuminousOpening,
                   double widthLuminousOpening, double lengthLuminousOpening, double heightLuminousOpening,
                   double[] horizontalAngles, double[] verticalAngles, double[] values) {
        this.iesna = iesna;
        this.tags = tags;
        this.tilt = tilt;
        this.initialRaterLumens = initialRaterLumens;
        this.multipliyingFactor = multipliyingFactor;
        this.numberVertical = numberVertical;
        this.numberHorizontal = numberHorizontal;
        this.unitsLuminousOpening = unitsLuminousOpening;
        this.widthLuminousOpening = widthLuminousOpening;
        this.lengthLuminousOpening = lengthLuminousOpening;
        this.heightLuminousOpening = heightLuminousOpening;
        this.horizontalAngles = horizontalAngles;
        this.verticalAngles = verticalAngles;
        this.values = values;
    }

    public String getIesna() {
        return iesna;
    }

    public void setIesna(String iesna) {
        this.iesna = iesna;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public double getInitialRaterLumens() {
        return initialRaterLumens;
    }

    public void setInitialRaterLumens(double initialRaterLumens) {
        this.initialRaterLumens = initialRaterLumens;
    }

    public double getMultipliyingFactor() {
        return multipliyingFactor;
    }

    public void setMultipliyingFactor(double multipliyingFactor) {
        this.multipliyingFactor = multipliyingFactor;
    }

    public int getNumberVertical() {
        return numberVertical;
    }

    public void setNumberVertical(int numberVertical) {
        this.numberVertical = numberVertical;
    }

    public int getNumberHorizontal() {
        return numberHorizontal;
    }

    public void setNumberHorizontal(int numberHorizontal) {
        this.numberHorizontal = numberHorizontal;
    }

    public int getUnitsLuminousOpening() {
        return unitsLuminousOpening;
    }

    public void setUnitsLuminousOpening(int unitsLuminousOpening) {
        this.unitsLuminousOpening = unitsLuminousOpening;
    }

    public double getWidthLuminousOpening() {
        return widthLuminousOpening;
    }

    public void setWidthLuminousOpening(double widthLuminousOpening) {
        this.widthLuminousOpening = widthLuminousOpening;
    }

    public double getLengthLuminousOpening() {
        return lengthLuminousOpening;
    }

    public void setLengthLuminousOpening(double lengthLuminousOpening) {
        this.lengthLuminousOpening = lengthLuminousOpening;
    }

    public double getHeightLuminousOpening() {
        return heightLuminousOpening;
    }

    public void setHeightLuminousOpening(double heightLuminousOpening) {
        this.heightLuminousOpening = heightLuminousOpening;
    }

    public double[] getHorizontalAngles() {
        return horizontalAngles;
    }

    public void setHorizontalAngles(double[] horizontalAngles) {
        this.horizontalAngles = horizontalAngles;
    }

    public double[] getVerticalAngles() {
        return verticalAngles;
    }

    public void setVerticalAngles(double[] verticalAngles) {
        this.verticalAngles = verticalAngles;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public Grid getGrid() {
        return new Grid(horizontalAngles, verticalAngles, values);
    }
}
