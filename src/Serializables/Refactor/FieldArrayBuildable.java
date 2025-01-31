package Serializables.Refactor;

import Serializables.Types.VarInt;

public class FieldArrayBuildable extends ArrayBuildable {
    protected final String countFieldName;
    public FieldArrayBuildable(String countFieldName, Buildable type) {
        super(new ClassBuildable(VarInt.class), type);
        this.countFieldName = countFieldName;
    }
}
