package pl.lukbed.opticalanalyser.mvc.model.project;

import java.util.ArrayList;
import java.util.List;

public class LightFunction {
    private String name = "default";
    private List<Measurement> measurements;

    public LightFunction() {
        measurements = new ArrayList<>(1);
    }

    public LightFunction(String name) {
        this();
        if (name.isEmpty()) {
            name = "default";
        }
        this.name = name;
    }

    public LightFunction(String name, List<Measurement> measurements) {
        this.name = name;
        this.measurements = measurements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public void addMeasurement(Measurement measurement) {
        measurements.add(measurement);
    }

    public void deleteMeasurement(Measurement measurement) {
        measurements.remove(measurement);
    }

    @Override
    public String toString() {
        return name;
    }
}
