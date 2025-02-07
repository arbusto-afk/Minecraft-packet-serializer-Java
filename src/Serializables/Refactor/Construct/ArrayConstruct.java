package Serializables.Refactor.Construct;

import Serializables.Refactor.Buildable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public   class ArrayConstruct implements ConstructItem {

    Class<?> clazz;
    ConstructItem[] items;

    @Override
    public Buildable clone() {
        return null;
    }

    public ArrayConstruct(Class<?> clazz, Object... constructs) {
        this.clazz = clazz;
        List<ConstructItem> con = new ArrayList<ConstructItem>();
        for(Object construct : constructs) {
            if(construct instanceof ConstructItem) {
                con.add((ConstructItem) construct);
            } else {
                throw new RuntimeException("Construct is not Buildable it is " + construct.getClass());
            }
        }
        items = con.toArray(new ConstructItem[con.size()]);
    }
}
