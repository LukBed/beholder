package pl.lukbed.opticalanalyser.mvc.model.project;

import pl.lukbed.opticalanalyser.mvc.model.regulations.Regulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private String name = "default project";

    private List<LightFunction> lightFunctions;
    private List<Regulation> regulations;

    public Project() {
        lightFunctions = new ArrayList<>(1);
        lightFunctions.add(new LightFunction());
        regulations = new ArrayList<>(1);
    }

    public Project(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LightFunction> getLightFunctions() {
        return lightFunctions;
    }

    public void setLightFunctions(List<LightFunction> lightFunctions) {
        this.lightFunctions = lightFunctions;
    }

    public void addLightFunction(LightFunction lightFunction) {
        lightFunctions.add(lightFunction);
    }

    public void addLightFunction() {
        lightFunctions.add(new LightFunction());
    }

    public List<Regulation> getRegulations() {
        return regulations;
    }

    public void setRegulations(List<Regulation> regulations) {
        this.regulations = regulations;
    }

    public void addRegulation(Regulation regulation) {
        regulations.add(regulation);
    }

    public void deleteRegulation(Regulation regulation) {
        regulations.remove(regulation);
    }
}