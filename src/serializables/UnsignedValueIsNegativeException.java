package serializables;

public class UnsignedValueIsNegativeException extends IllegalArgumentException {
    public UnsignedValueIsNegativeException(String message) {
        super(message);
    }
    public UnsignedValueIsNegativeException(Integer n ){
        throw new IllegalArgumentException("Unsigned value must be greater than 0, received: " + n);
    }
}
