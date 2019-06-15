package pl.lukbed.opticalanalyser.mvc.model.regulations;

public enum PhotometryStatus {
    UNKNOWN("unknown", StatusColor.DEFAULT),
    TL_OK("customer requirements OK", StatusColor.GREEN),
    ECE_OK_TL_NOK ("ECE OK, customer requirements NOK", StatusColor.YELLOW),
    ECE_OK("ECE OK", StatusColor.GREEN),
    ECE_NOK("ECE NOK", StatusColor.RED),
    ECE_NOK_COP_OK("COP OK, ECE NOK", StatusColor.YELLOW),
    COP_NOK("COP NOK", StatusColor.RED);

    private String describtion;
    private StatusColor color;

    PhotometryStatus(String describtion, StatusColor color) {
        this.describtion = describtion;
        this.color = color;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public StatusColor getColor() {
        return color;
    }

    public void setColor(StatusColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return describtion;
    }
}