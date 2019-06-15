package pl.lukbed.opticalanalyser.mvc.model.regulations;

import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Grid;
import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Point;

import java.util.ArrayList;
import java.util.List;

//wymagania wg danej normy
public class Regulation {

    private String name;
    private List<PointRegulation> points = new ArrayList<>(50);
    private double tlMin = 1;
    private double tlMax = 1;
    private double copMin = 0.8;
    private double copMax = 0.8;

    public Regulation(String name) {
        this.name = name;
    }

    public Regulation(String name, List<PointRegulation> points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void addPoint(PointRegulation pointRegulation) {
        points.add(pointRegulation);
    }

    public void addPoint (double horizontal, double vertival, double minimumValue, double maximumValue) {
        points.add(new PointRegulation(horizontal, vertival, minimumValue, maximumValue));
    }

    public String toString() {
        /*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append("\n");
        for (PointRegulation point : points) {
            stringBuilder.append(point);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();*/
        return name;
    }

    public String checkGrid(Grid grid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for (PointRegulation point : points) {
            Point p = grid.checkPoint(point.getHorizontal(), point.getVertival());
            PhotometryMessage message = point.checkPoint(p);
            stringBuilder.append(message.print());
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }


}
