package serializables.Complex;

import serializables.ComplexType;

import java.util.List;

public class UnsupComplexMultiple implements ComplexType {
    @Override
    public List<Class<?>> getBuilder() {
        return List.of(UnsupComplexMultiple.class);
    }
}
