package pl.lukbed.opticalanalyser.mvc.model.files;

public class DataOperations {

    public static Tag makeTag(String text) {
        int start = text.indexOf("[");
        int finish = text.indexOf("]");
        String name = text.substring(start+1, finish-1);

        String describion;
        if (finish == text.length()-1) {
            describion = "";
        }
        else if (text.charAt(finish+1) == ' ') {
            describion = text.substring(finish+2);
        } else {
            describion = text.substring(finish + 1);

        }

        return new Tag(name, describion);
    }
}
