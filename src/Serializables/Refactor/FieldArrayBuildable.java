package Serializables.Refactor;

import Serializables.Consts;
import Serializables.NativeTypesEnum;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FieldRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.VarInt;
import Serializables.Types.Void;

import java.util.ArrayList;
import java.util.List;

public class FieldArrayBuildable extends PrefArrayBuildable {
    protected final String countFieldName;
    public FieldArrayBuildable(String countFieldName, Flattenable type) {
        super(NativeTypesEnum.VARINT.getClassBuildable(), type);
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

    @Override
    public List<PacketField> asArrayFields() {
        return this.asPacketFields();
    }

    @Override
    public List<PacketField> asPacketFields() {
        //pref array always return a list of 1 element
//        List<PacketField> aux = super.asPacketFields();
    //    aux.getFirst().getSsrb().overrideDeserializerRef(new FuncRef(Consts.FIELDARRAYDESERIALIZER.toString(), List.of(new ArgRef(countFieldName))));
        PacketField field = super.asPacketFields().getFirst();
        FuncRef aux = (FuncRef) field.getDeserializerMethod();
        List<RefBuilder> modifargs = new ArrayList<>(aux.getArgs());
        modifargs.add(new FieldRef(countFieldName));

        RefBuilder deserializerRef =  new FuncRef(Consts.FIXEDARRAYDESERIALIZER.toString(), modifargs);
        RefBuilder serializerRef =  new ArgRef("fieldARRWRITE");
        return List.of(new PacketField("", "", field.getSsrb(), deserializerRef, serializerRef));
    }
}
