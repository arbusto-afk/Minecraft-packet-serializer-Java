package Serializables.Refactor;

import Serializables.Types.VarInt;

public class FixedArrayBuildable extends ArrayBuildable {
    protected final int size;
    public FixedArrayBuildable(int size, Buildable type) {
        super(new ClassBuildable(VarInt.class), type);
        this.size = size;
    }
}
