package pl.lukbed.opticalanalyser.mvc.model.regulations;

//status of point in reference to regulation
public class PhotometryMessage {
    private String text;
    private PhotometryStatus status;

    public PhotometryMessage(String text, PhotometryStatus status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PhotometryStatus getStatus() {
        return status;
    }

    public void setStatus(PhotometryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return text + " - " + status.getColor();
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        if (status.getColor().name() == "RED") {
            stringBuilder.append("<td style=\"color: red;\">");
        } else {
            stringBuilder.append("<td>");
        }
        stringBuilder.append(toString());
        stringBuilder.append("</tr>");
        return stringBuilder.toString();
    }
}
