package Serializables.Complex;

import java.lang.reflect.Type;
import java.util.List;

public class UnsupNativeUsedInPrimSC implements ComplexType {
    @Override
    public List<TypeDescriptor> getBuilder() {
        return List.of(new TypeDescriptor(UnsupNativeUsedInPrimSC.class, ":("));
    }
}
