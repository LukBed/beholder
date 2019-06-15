package pl.lukbed.opticalanalyser.mvc.model.regulations;

class PointCustomRegulation extends PointRegulation {

    private double tlMin = 1;
    private boolean useTlMin = false;
    private double tlMax = 1;
    private boolean useTlMax = false;
    private double copMin = 0.8;
    private boolean useCopMin = false;
    private double copMax = 0.8;
    private boolean useCopMax = false;

    public PointCustomRegulation(double horizontal, double vertival, double minimumValue, double maximumValue) {
        super(horizontal, vertival, minimumValue, maximumValue);
    }

    public PointCustomRegulation(double horizontal, double vertival, double minimumValue, double maximumValue, double tlMin) {
        super(horizontal, vertival, minimumValue, maximumValue);
        this.tlMin = Math.abs(tlMin);
        useTlMin = true;
    }

    public PointCustomRegulation(double horizontal, double vertival, double minimumValue, double maximumValue, double tlMin, double tlMax) {
        this(horizontal, vertival, minimumValue, maximumValue, tlMin);
        this.tlMax = Math.abs(tlMax);
        useTlMax = true;
    }

    public PointCustomRegulation(double horizontal, double vertival, double minimumValue, double maximumValue, double tlMin, double tlMax, double copMin) {
        this(horizontal, vertival, minimumValue, maximumValue, tlMin, tlMax);
        this.copMin = Math.abs(copMin);
        useCopMin = true;
    }

    public PointCustomRegulation(double horizontal, double vertival, double minimumValue, double maximumValue, double tlMin, double tlMax, double copMin, double copMax) {
        this(horizontal, vertival, minimumValue, maximumValue, tlMin, tlMax, copMin);
        this.copMax = Math.abs(copMax);
        useCopMax = true;
    }

    public double getTlMin() {
        return tlMin;
    }

    public void setTlMin(double tlMin) {
        this.tlMin = tlMin;
        useTlMin = true;
    }

    public void resetTlMin() {
        tlMin = 1;
        useTlMin = false;
    }

    public double getTlMax() {
        return tlMax;
    }

    public void setTlMax(double tlMax) {
        this.tlMax = tlMax;
        useTlMax = true;
    }

    public void resetTlMax() {
        tlMax = 1;
        useTlMax = false;
    }

    public double getCopMin() {
        return copMin;
    }

    public void setCopMin(double copMin) {
        this.copMin = copMin;
        useCopMin = true;
    }

    public void resetCopMin() {
        copMin = 0.8;
        useCopMin = false;
    }

    public double getCopMax() {
        return copMax;
    }

    public void setCopMax(double copMax) {
        this.copMax = copMax;
        useCopMax = true;
    }

    public void resetCopMax() {
        copMin = 0.8;
        useCopMax = false;
    }


}
