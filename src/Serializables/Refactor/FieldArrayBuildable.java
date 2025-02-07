package Serializables.Refactor;

import Serializables.Types.VarInt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FieldArrayBuildable extends PrefArrayBuildable {
    protected final String countFieldName;
    public FieldArrayBuildable(String countFieldName, Buildable type) {
        super(new ClassBuildable(VarInt.class), type);
        this.countFieldName = countFieldName;
    }

    @Override
    public Buildable clone() {
        return new FieldArrayBuildable(countFieldName, type);
    }

    @Override
    public String toString() {
        return "FieldArrayBuildable{" +
                "countFieldName='" + countFieldName + '\'' +
                '}';
    }

    @Override
    public Object getClasses() {
        if(type instanceof ClassBuildable clb){
            List<Object> arrayDesc = new ArrayList<>();
            arrayDesc.add(Array.newInstance((Class<?>) clb.getClasses(), 0).getClass());
            arrayDesc.add("fieldArray:'-1'");
            return arrayDesc;
        }
        return this;
    }
}
