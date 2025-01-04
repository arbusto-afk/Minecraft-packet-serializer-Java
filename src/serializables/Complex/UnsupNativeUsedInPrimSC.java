package serializables.Complex;

import serializables.ComplexType;

import java.util.List;

public class UnsupNativeUsedInPrimSC implements ComplexType {
    @Override
    public List<Class<?>> getBuilder() {
        return List.of(UnsupNativeUsedInPrimSC.class);
    }
}
