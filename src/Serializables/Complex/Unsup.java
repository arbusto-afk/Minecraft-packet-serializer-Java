package Serializables.Complex;

import java.util.List;

public class Unsup implements ComplexType {
    public String from;

    public Unsup(String from) {
        this.from = from;
    }

    @Override
    public List<TypeDescriptor> getBuilder() {
        return List.of(new TypeDescriptor(this.getClass(), ":((("));
    }
}
