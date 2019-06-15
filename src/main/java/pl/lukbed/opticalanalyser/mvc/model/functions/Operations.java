package pl.lukbed.opticalanalyser.mvc.model.functions;


import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Point;

public class Operations {

    //zwraca dystans pomiędzy zadanymi współrzędnymi
    public static double checkDistance(double x1, double y1, double x2, double y2) {
        double dx = Math.abs(x1-x2);
        double dy = Math.abs(y1-y2);
        return Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
    }

    //sprawdza dystans pomiędzy dwoma punktami
    public static double checkDistance(Point p1, Point p2) {
        return checkDistance(p1.getHorizontal(), p1.getVertical(), p2.getHorizontal(), p2.getVertical());
    }

    //sprawdza dystans pomiędzy współrzędnymi i zadanym punktem
    public static double checkDistance(double h, double v, Point p) {
        return checkDistance(h, v, p.getHorizontal(), p.getVertical());
    }

    //tworzy niezależną kopię tablicy dwuwymiarowej
    public static double[][] cloneArray(double[][] in) {
        double[][] out = new double[in.length][];
        for (int i = 0; i < in.length; i++) {
            out[i] = in[i].clone();
        }
        return out;
    }

    //tworzy tablicę kolejnych wartości w zadanym zakresie o określonym kroku
    public static double[] createSteps(double min, double max, double step) {
        int stepsQty;

        if (max<min) {
            double buffor = max;
            max = min;
            min = buffor;
        }

        if (max == min)
            stepsQty = 1;
        else
            stepsQty= (int) ((max-min)/step);

        double[] t = new double[stepsQty];

        for (int i = 0; i < stepsQty; i++) {
            t[i] = min+i*step;
        }

        return t;
    }

}