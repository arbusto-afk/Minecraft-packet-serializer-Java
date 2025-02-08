package Serializables.Refactor;

import Serializables.Types.VarInt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FixedArrayBuildable extends PrefArrayBuildable {
    protected final int size;
    public FixedArrayBuildable(int size, Buildable type) {
        super(new ClassBuildable(VarInt.class), type);
        this.size = size;
    }

    @Override
    public Buildable clone() {
        return new FixedArrayBuildable(size, type);
    }

    @Override
    public String toString() {
        return "FixedArrayBuildable{" +
                "size=" + size +
                '}';
    }

    @Override
    public Object flatten() {
        if(type instanceof ClassBuildable clb){
            List<Object> arrayDesc = new ArrayList<>();
            arrayDesc.add(Array.newInstance((Class<?>) clb.flatten(), 0).getClass());
            arrayDesc.add(size);
            return arrayDesc;
        }
        return this;
    }
}
