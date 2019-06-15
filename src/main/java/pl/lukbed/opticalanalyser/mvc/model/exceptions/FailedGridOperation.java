package pl.lukbed.opticalanalyser.mvc.model.exceptions;

public class FailedGridOperation extends RuntimeException {
    public FailedGridOperation(String message) {
        super(message);
    }
}
