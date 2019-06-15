package pl.lukbed.opticalanalyser.mvc.model;

import pl.lukbed.opticalanalyser.mvc.model.project.Project;

public abstract class ThisApplication {

    private static Project project;
    private static final String appName = "Beholder pre-alfa v. 0.0.19.06";

    public static void start () {
        project = new Project("default project");
    }

    public static Project getProject() {
        return project;
    }

    public static String getAppName() {
        return appName;
    }

    public static void setProject(Project project) {
        ThisApplication.project = project;
    }
}