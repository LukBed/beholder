package pl.lukbed.opticalanalyser.mvc.model.functions;

import pl.lukbed.opticalanalyser.mvc.model.exceptions.InvalidInputException;
import pl.lukbed.opticalanalyser.mvc.model.exceptions.UnexpectedEndOfFileException;
import pl.lukbed.opticalanalyser.mvc.model.files.DataOperations;
import pl.lukbed.opticalanalyser.mvc.model.files.IesFile;
import pl.lukbed.opticalanalyser.mvc.model.files.Tag;
import pl.lukbed.opticalanalyser.mvc.model.regulations.PointRegulation;
import pl.lukbed.opticalanalyser.mvc.model.regulations.Regulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class OpticalFileReader {

    /*

     */

    //odczytywanie informacji o wymaganiach normy z zewnętrznego pliku
    public static Regulation readRegulation(File file) throws FileNotFoundException{

        Scanner scanner = new Scanner(file);

        //odczytywanie nagłówka
        String title = scanner.nextLine();
        //pomijanie drugiej linii - zawiera opis H, V, min, max
        scanner.nextLine();

        int i =0;
        double horizontal = 0;
        double vertical = 0;
        double minValue = 0;
        double maxValue = 0;
        List<PointRegulation> points = new ArrayList<>(50);

        while (scanner.hasNextDouble()) {
            i++;
            if (i == 1) {
                horizontal = scanner.nextDouble();
            } else if (i == 2) {
                vertical = scanner.nextDouble();
            } else if (i == 3) {
                minValue = scanner.nextDouble();
            } else if (i == 4) {
                maxValue = scanner.nextDouble();
                points.add(new PointRegulation(horizontal, vertical, minValue, maxValue));

                i = 0;
            } else {
                throw new InvalidInputException("file not read");
            }
        }

        scanner.close();
        return new Regulation(title, points);
    }

    public static IesFile readIes(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        scanner.useLocale(Locale.US);

        String iesna = scanner.nextLine(); // IESNA91
        String text;
        List<Tag> tags = new ArrayList<>(1); // f. ex. [TEST] Project xxx Tail LH T1


        do {
            text = scanner.nextLine();
            if (text.charAt(0) == '[') {
                tags.add(DataOperations.makeTag(text));
            }
        } while(text.charAt(0) == '['); //TILT=NONE

        text = text.replaceAll(" ", "");
        String tilt = text.substring(5); // default TILT=NONE

        scanner.nextDouble(); //just 1

        double initialRaterLumens = scanner.nextDouble(); // -1 the initial rated lumens or just -1 (default)
        double multipliyingFactor = scanner.nextDouble(); // multiplying factor
        int numberVertical = scanner.nextInt(); // number of vertical angles
        int numberHorizontal = scanner.nextInt(); // number of horizontal angles

        scanner.nextDouble(); //just 1
        int unitsLuminousOpening = scanner.nextInt(); // 1 units of dimension of luminous opening, 1 for feet, 2 for meters

        double widthLuminousOpening = scanner.nextDouble(); // 0 the width of luminous opening, default 0
        double lengthLuminousOpening = scanner.nextDouble(); // 0 the length of luminous opening, default 0
        double heightLuminousOpening = scanner.nextDouble(); // 0 the height of luminous opening, default 0

        scanner.nextInt(); // 1 just 1
        scanner.nextInt(); // 1 just 1
        scanner.nextInt(); // 0 just 0

        double[] verticalAngles = new double[numberVertical];
        for (int i = 0; i < numberVertical; i++) {
            verticalAngles[i] = scanner.nextDouble();
        }

        double[] horizontalAngles = new double[numberHorizontal];
        for (int i = 0; i < numberHorizontal; i++) {
            horizontalAngles[i] = scanner.nextDouble();
        }

        double[] values = new double[numberHorizontal*numberVertical];
        for (int i = 0; i < numberHorizontal*numberVertical; i++) {
            values[i] = scanner.nextDouble();
        }

        if(scanner.hasNextLine()) {
            scanner.nextLine();
        }

        if (scanner.hasNextLine()) {
           throw new UnexpectedEndOfFileException("unexpected file format");
        }

        return new IesFile(iesna, tags, tilt, initialRaterLumens, multipliyingFactor,
        numberVertical, numberHorizontal, unitsLuminousOpening,
        widthLuminousOpening, lengthLuminousOpening, heightLuminousOpening,
        horizontalAngles, verticalAngles, values);
    }



}