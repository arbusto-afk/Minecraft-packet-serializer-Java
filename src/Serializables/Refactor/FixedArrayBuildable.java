package Serializables.Refactor;

import Serializables.Consts;
import Serializables.NativeTypesEnum;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Types.VarInt;

import java.util.List;

public class FixedArrayBuildable extends PrefArrayBuildable {
    protected final int size;
    public FixedArrayBuildable(int size, Flattenable type) {
        super(NativeTypesEnum.VARINT.getClassBuildable(), type);
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

    @Override
    public List<PacketField> asPacketFields() {
        //pref array always return a list of 1 element
        List<PacketField> aux = super.asPacketFields();
          return aux;
    }
}
