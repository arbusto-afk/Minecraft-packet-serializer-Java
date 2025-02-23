package Serializables.Refactor;

import Serializables.Types.VarInt;

public class FieldArrayBuildable extends PrefArrayBuildable {
    protected final String countFieldName;
    public FieldArrayBuildable(String countFieldName, Flattenable[] type) {
        super(new ClassBuildable(VarInt.class), type);
        this.countFieldName = countFieldName;
    }

    @Override
    public Flattenable clone() {
        return new FieldArrayBuildable(countFieldName, type);
    }

    @Override
    public String toString() {
        return "FieldArrayBuildable{" +
                "countFieldName='" + countFieldName + '\'' +
                '}';
    }

/*
    @Override
    public Object flatten() {
        if(type instanceof ClassBuildable clb){
            List<Object> arrayDesc = new ArrayList<>();
            arrayDesc.add(Array.newInstance((Class<?>) clb.flatten(), 0).getClass());
            arrayDesc.add("fieldArray:'-1'");
            return arrayDesc;
        }
        return this;
    }
    */

}
