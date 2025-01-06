package Serializables;

public class UnexpectedJsonFormatException extends IllegalArgumentException {
    public UnexpectedJsonFormatException(String message) {
        super(message);
    }
    public UnexpectedJsonFormatException(Object obj){
        throw new IllegalArgumentException("Unexpected JSON format " + obj);
    }
}
