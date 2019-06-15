package pl.lukbed.opticalanalyser.mvc.model.project;

import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Grid;

abstract public class Measurement {
    private String name = "default";

    public Measurement() {
    }

    public Measurement(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void createHtml();

    @Override
    public String toString() {
        return name;
    }

    public Grid getGrid() {
        return null;
    }
}
