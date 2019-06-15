package pl.lukbed.opticalanalyser.mvc.model.project;

import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Grid;

public class GridMeasurement extends Measurement {

    private Grid grid;

    public GridMeasurement(Grid grid) {
        this.grid = grid;
    }

    public GridMeasurement(String name, Grid grid) {
        super(name);
        this.grid = grid;
    }

    @Override
    public void createHtml() {
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
