package pl.lukbed.opticalanalyser.mvc.model.project;

import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Point;

import java.util.Map;

public class PointMeasurement extends Measurement {


    //czy double jako h i v się sprawdzą? czy to nie będzie zbyt niedokładne?
    Map<Position, Double> points;

    @Override
    public void createHtml() {

    }

    private class Position {
        private double H;
        private double V;
    }
}
