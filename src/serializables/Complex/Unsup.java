package serializables.Complex;

import serializables.ComplexType;
import serializables.ProtocolType;

import java.util.List;

public class Unsup implements ComplexType {
    public String from;

    public Unsup(String from) {
        this.from = from;
    }

    @Override
    public List<Class<?>> getBuilder() {
        return List.of(Unsup.class);
    }
}
