package Serializables.Refactor.Construct.Arrays;

import Serializables.ProtocolType;
import Serializables.Refactor.Construct.ConstructItem;

import java.util.ArrayList;
import java.util.List;

public class AbsArray implements ConstructItem {
    protected final Class<?>[] classes;
    protected  List<Object[]> dataArrs;

    public AbsArray(Class<?>[] classes, Object... dataArrs) {
        this.classes = classes;
        this.dataArrs = new ArrayList<>();
        for(Object o : dataArrs) {
            this.dataArrs.add((Object[])o);
        }
    }
    @Override
    public AbsArray clone() {
        try {
            AbsArray cloned = (AbsArray) super.clone(); // Use Object's clone method
            cloned.dataArrs = new ArrayList<>(this.dataArrs); // Deep copy the list
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }
}
