package pl.lukbed.opticalanalyser.mvc.model.exceptions;

public class UnexpectedEndOfFileException extends RuntimeException {
    public UnexpectedEndOfFileException(String message) {
        super(message);
    }
}
