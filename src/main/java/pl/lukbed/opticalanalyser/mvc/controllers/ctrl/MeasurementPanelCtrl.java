package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class MeasurementPanelCtrl {

    public static void clearRaport() {
        File file = new File(MainCtrl.getRaportUrl());
        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                fileExists = file.createNewFile();
                CommunicationCtrl.print("Raport file created");
            } catch (IOException e) {
                CommunicationCtrl.print("Raport file not created");
            }
        }
        try (
                var fileWriter = new FileWriter(MainCtrl.getRaportUrl());
                var writer = new BufferedWriter(fileWriter);
        ) {
            writer.write("");
        } catch (IOException e) {
            CommunicationCtrl.print("Can not save raport file");
        }
    }

}
