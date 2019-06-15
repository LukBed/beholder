package pl.lukbed.opticalanalyser.mvc.model.photometricGrid;


import pl.lukbed.opticalanalyser.mvc.model.exceptions.FailedGridOperation;
import pl.lukbed.opticalanalyser.mvc.model.exceptions.InvalidInputException;
import pl.lukbed.opticalanalyser.mvc.model.functions.Operations;

import java.util.Arrays;

// siatka pomiarowa
public class Grid {



    private double[] horizontalGrid;
    private double[] verticalGrid;
    private double[][] photometricalValues;
    private double translationH = 0;
    private double translationV = 0;
    private double factor = 1;
    private boolean inverted = false;

    public Grid(double[] horizontalGrid, double[] verticalGrid, double[] values) {
        if (horizontalGrid.length*verticalGrid.length!=values.length) {
            throw new InvalidInputException("Wrong size of grid in input data");
        }

        int k = 0;
        this.horizontalGrid = horizontalGrid;
        this.verticalGrid = verticalGrid;
        photometricalValues = new double[horizontalGrid.length][verticalGrid.length];
        for (int i = 0; i < horizontalGrid.length; i++) {
            for (int j = 0; j < verticalGrid.length; j++) {
                photometricalValues[i][j] = values[k];
                k++;
            }
        }
    }

    public Grid(double[] horizontalGrid, double[] verticalGrid, double[][] photometricalValues) {
        if (horizontalGrid.length*verticalGrid.length != photometricalValues[0].length*photometricalValues.length) {
            throw new InvalidInputException("Wrong size of grid in input data");
        }

        this.horizontalGrid = horizontalGrid;
        this.verticalGrid = verticalGrid;
        this.photometricalValues = photometricalValues;

    }

    public Grid (double[] horizontalGrid, double[] verticalGrid, double[][] photometricalValues, double translationH, double translationV, double factor) {
        this(horizontalGrid, verticalGrid, photometricalValues);
        this.translationH = translationH;
        this.translationV = translationV;
        this.factor = factor;
    }

    public Grid (double[] horizontalGrid, double[] verticalGrid, double[][] photometricalValues, double translationH, double translationV, double factor, boolean inverted) {
        this(horizontalGrid, verticalGrid, photometricalValues, translationH, translationV, factor);
        this.inverted = inverted;
    }

    public double getTranslationH() {
        return translationH;
    }

    public double getTranslationV() {
        return translationV;
    }

    public double getFactor() {
        return factor;
    }

    public boolean getInverted() {
        return inverted;
    }

    @Override
    //zwraca wszystkie punkty siatki
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < horizontalGrid.length; i++) {
            for (int j = 0; j < verticalGrid.length; j++) {
                stringBuilder.append("H " + horizontalGrid[i] + " V " + verticalGrid[j] + ": " + photometricalValues[i][j] + " Cd\n");
            }
        }
        return stringBuilder.toString();
    }

    //sprawdza wartość fotometryczną w punkcie lub ją generuje, jeśli jest w środku siatki, ale poza punktami pomiarowymi
    public Point checkPoint(double horizontal, double vertical)
            throws FailedGridOperation, ArrayIndexOutOfBoundsException {
        int indexMoreH = -1;
        int indexLessH = -1;
        int indexMoreV = -1;
        int indexLessV = -1;

        if (horizontal<horizontalGrid[0] || horizontal>horizontalGrid[horizontalGrid.length-1]
        || vertical<verticalGrid[0] || vertical>verticalGrid[verticalGrid.length-1]) {
            return new Point(horizontal, vertical, 0);
        }

        for (int i = 0; i < horizontalGrid.length; i++) {
            if (horizontalGrid[i]>=horizontal) {
                indexMoreH = i;
                break;
            }
        }

        for (int i = horizontalGrid.length-1; i >= 0; i--) {
            if (horizontalGrid[i]<=horizontal) {
                indexLessH = i;
                break;
            }
        }

        for (int i = 0; i < verticalGrid.length; i++) {
            if (verticalGrid[i]>=vertical) {
                indexMoreV = i;
                break;
            }
        }

        for (int i = verticalGrid.length-1; i >= 0; i--) {
            if (verticalGrid[i]<=vertical) {
                indexLessV = i;
                break;
            }
        }

        if (indexMoreH == -1 || indexLessH == -1 || indexMoreV == -1 || indexLessV == -1) {
            throw new FailedGridOperation("Searched point not found");
        }

        if (indexLessH == indexMoreH && indexLessV == indexMoreV) {
            return getPointIndex(indexLessH, indexLessV);
        } else if (indexLessH != indexMoreH && indexLessV != indexMoreV) {
            Point[] points = new Point[4];
            points[0] = getPointIndex(indexLessH, indexLessV);
            points[1] = getPointIndex(indexMoreH, indexMoreV);
            points[2] = getPointIndex(indexLessH, indexMoreV);
            points[3] = getPointIndex(indexMoreH, indexLessV);

            return generatePoint(horizontal, vertical, points);
        } else {
            Point[] points = new Point[2];
            points[0] = getPointIndex(indexLessH, indexLessV);
            points[1] = getPointIndex(indexMoreH, indexMoreV);

            return generatePoint(horizontal, vertical, points);
        }
    }

    //tworzy niezależną kopię siatki
    public Grid cloneGrid() {
        double[] h = Arrays.copyOf(horizontalGrid, horizontalGrid.length);
        double[] v = Arrays.copyOf(verticalGrid, verticalGrid.length);
        double[][] pV = Operations.cloneArray(photometricalValues);
        return new Grid(h, v, pV, translationH, translationV, factor, inverted);
    }

    /*przesuwa siatkę o zadane wartości
    podajemy finalne przesunięcie - jeśli chcemy przesunąć o 5, a potem o kolejne 5, to przy drugim razie podajemy 10*/
    public Grid translate(double finalHorizontal, double finalVertical) {
        double deltaHorizontal = finalHorizontal-translationH;
        double deltaVertical = finalVertical-translationV;

        double[] h = Arrays.copyOf(horizontalGrid, horizontalGrid.length);
        double[] v = Arrays.copyOf(verticalGrid, verticalGrid.length);
        double[][] pV = Operations.cloneArray(photometricalValues);

        for (int i = 0; i < h.length; i++) {
            h[i] += deltaHorizontal;
        }

        for (int i = 0; i < v.length; i++) {
            v[i] += deltaVertical;
        }

        return new Grid(h, v, pV, finalHorizontal, finalVertical, factor, inverted);

    }

    //dodaje współczynnik do wszystkich punktów siatki
    public Grid addFactor(double finalFactor) {
        if (finalFactor<0) {
            finalFactor = -finalFactor;
        }

        double deltaFactor = finalFactor/factor;

        double[] h = Arrays.copyOf(horizontalGrid, horizontalGrid.length);
        double[] v = Arrays.copyOf(verticalGrid, verticalGrid.length);
        double[][] pV = Operations.cloneArray(photometricalValues);

        for (int i = 0; i < pV.length; i++) {
            for (int j = 0; j < pV[i].length; j++) {
                    pV[i][j] *= deltaFactor;
            }
        }

        return new Grid(h, v, pV, translationH, translationV, finalFactor, inverted);
    }

    //odbija siatkę względem H=0
    public Grid invert() {
        double[] tempH = Arrays.copyOf(horizontalGrid, horizontalGrid.length);
        double[] h = new double[tempH.length];
        double[] v = Arrays.copyOf(verticalGrid, verticalGrid.length);
        double[][] tempPV = Operations.cloneArray(photometricalValues);
        double[][] pV = new double[tempPV.length][tempPV[0].length];

        for (int i = 0; i < tempH.length; i++) {
            h[i] = -tempH[tempH.length-1-i];
            pV[i] = tempPV[tempH.length-1-i];
        }

        return new Grid(h, v, pV, -translationH, translationV, factor, !inverted);
    }

    //zwraca oryginalną siatkę, tzn. bez przesunięcia, współczynnika i odbicia
    public Grid nominal() {
        Grid outputGrid = cloneGrid();

        if (outputGrid.getFactor() != 1)
            outputGrid = outputGrid.addFactor(1);

        if (outputGrid.getTranslationH() != 0 || outputGrid.getTranslationV() != 0)
            outputGrid = outputGrid.translate(0, 0);


        if (outputGrid.getInverted())
            outputGrid = outputGrid.invert();

        return outputGrid;
    }

    //tworzy siatkę na podstawie istniejącej o zadanym obszarze i kroku pomiarowym
    public Grid create(double hStart, double hFinish, double hStep, double vStart, double vFinish, double vStep) {
        double[] newH = Operations.createSteps(hStart, hFinish, hStep);
        double[] newV = Operations.createSteps(vStart, vFinish, vStep);
        double[][] newPV = new double[newH.length][newV.length];
        for (int i = 0; i < newH.length; i++) {
            for (int j = 0; j < newV.length; j++) {
                newPV[i][j] = checkPoint(newH[i], newV[j]).getValue();
            }
        }

        return new Grid(newH, newV, newPV);
    }

    //zwraca punkt z siatki o wskazanym indeksie H/V
    private Point getPointIndex(int horizontalIndex, int verticalIndex) throws ArrayIndexOutOfBoundsException {
        return new Point(horizontalGrid[horizontalIndex], verticalGrid[verticalIndex], photometricalValues[horizontalIndex][verticalIndex]);
    }

    //generuje wartość w punkcie na podstawie tablicy sąsiednich punktów
    private Point generatePoint(double horizontal, double vertical, Point points[]) throws FailedGridOperation {
        double value;

        double summValuesProductWeight = 0; //suma iloczynów wartośi w punkcie oraz wagi
        double sumWeight = 0; //suma wag, czyli odwrotności dystansu
        double tempWeight;

        for (Point p : points)
        {
            tempWeight = 1/Operations.checkDistance(horizontal, vertical, p);
            summValuesProductWeight += p.getValue()*tempWeight;
            sumWeight += tempWeight;
        }

        if (sumWeight <= 0 || summValuesProductWeight<0) {
            throw new FailedGridOperation("Photometrical value not found");
        }

        value = summValuesProductWeight/sumWeight;
        return new Point(horizontal, vertical, value);
    }

}