package pl.lukbed.opticalanalyser.mvc.model.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
