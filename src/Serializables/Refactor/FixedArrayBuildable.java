package Serializables.Refactor;

import Serializables.Types.VarInt;

public class FixedArrayBuildable extends PrefArrayBuildable {
    protected final int size;
    public FixedArrayBuildable(int size, Flattenable[] type) {
        super(new ClassBuildable(VarInt.class), type);
        this.size = size;
    }

    @Override
    public Flattenable clone() {
        return new FixedArrayBuildable(size, type);
    }

    @Override
    public String toString() {
        return "FixedArrayBuildable{" +
                "size=" + size +
                '}';
    }
//
//    @Override
//    public Object flatten() {
//        if(type instanceof ClassBuildable clb){
//            List<Object> arrayDesc = new ArrayList<>();
//            arrayDesc.add(Array.newInstance((Class<?>) clb.flatten(), 0).getClass());
//            arrayDesc.add(size);
//            return arrayDesc;
//        }
//        return this;
//    }
}
