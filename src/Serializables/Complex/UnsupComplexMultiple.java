package Serializables.Complex;

import java.util.List;

public class UnsupComplexMultiple implements ComplexType {
    String s;

    public UnsupComplexMultiple(String s) {
        this.s = s;
    }

    public UnsupComplexMultiple() {
        s = ":^(";
    }

    @Override
    public List<TypeDescriptor> getBuilder() {
        return List.of(new TypeDescriptor(UnsupComplexMultiple.class, s));
    }
}
