package Serializables.Refactor;

import Serializables.Consts;
import Serializables.Refactor.RefBuilder.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BitfieldBuildable extends ContainerBuildable {

    public BitfieldBuildable(BitfieldField[] flattenable) {
        super(flattenable);
    }

    @Override
    public List<PacketField> asPacketFields() {
        List<PacketField> fields = super.asPacketFields();
        int size = 0;
        for(Flattenable b : buildables){
            BitfieldField bf = (BitfieldField)b;
            size += bf.getSize();
        }
        RefBuilder desRef;
        Class<?> clazz;
        switch(size){
            case 8 -> {
                clazz = Byte.class;
                desRef = Consts.I8DESERIALIZER.getF();
            }
            case 16 -> {
                clazz = Short.class;
                desRef = Consts.I16DESERIALIZER.getF();
            }
            case 32 -> {
                clazz = Integer.class;
                desRef = Consts.I32DESERIALIZER.getF();
            }
            case 64 -> {
                desRef = Consts.I64DESERIALIZER.getF();
                clazz = Long.class;
            }
            default -> throw new IllegalStateException("Unexpected bitfield size value: " + size);
        }
        RefBuilder deserializerRef = desRef;
        RefBuilder serializerRef = new ArgRef("NOSERREFFORBITFIELD@BITFIELDBUILDABLE");
        PacketField bfbCopyField = new PacketField("", "", Long.class, deserializerRef,serializerRef);
        List<PacketField> newFields = new ArrayList<PacketField>();
        newFields.add(bfbCopyField);
        int moved = 0;
        for(Flattenable fl : super.buildables){
            PacketField f = fl.asPacketFields().getFirst();
            BitfieldField bitfield = (BitfieldField) fl;
            int ssize = bitfield.size;

         /*   ******** >> (64-32)32;
            ____****....
            --------;
            ******** >> (64 - 32 + 32)
            1
      */      long mask = (((1L >>(64 - ssize + moved)) - 1)) & (1 << ssize); // Create bit mask
            mask = 1;
            RefBuilder fDesRef = new BitmaskRef(new FieldRef(""), new ArgRef(String.valueOf(mask)));

            PacketField p = new PacketField(
                    bitfield.getName(),
                    f.getDesc(),
                    Integer.class,
                    fDesRef,
                    serializerRef
            );

            newFields.add(p);
            moved += ssize;
        }
        return newFields;
    }


    @Override
    public List<PacketField> asArrayFields() {
        //  List<PacketField> fields = super.asArrayFields();
        List<PacketField> fields = new ArrayList<>();
        int size = 0;
        for (Flattenable b : buildables) {
            BitfieldField bf = (BitfieldField) b;
            size += bf.getSize();
        }
        Class<?> clazz;
        RefBuilder desRef;
        RefBuilder lastDesRef;
        switch(size){
            case 8 -> {
                lastDesRef = Consts.I8DESERIALIZER.getF();
                desRef = Consts.PEEKI8.getF();
            }
            case 16 -> {
                lastDesRef = Consts.I16DESERIALIZER.getF();
                desRef = Consts.PEEKI16.getF();
            }
            case 32 -> {
                lastDesRef = Consts.I32DESERIALIZER.getF();
                desRef = Consts.PEEKI32.getF();
            }
            case 64 -> {
                desRef = Consts.PEEKI64.getF();
                lastDesRef = Consts.PEEKI64.getF();
            }
            default -> throw new IllegalStateException("Unexpected bitfield size value: " + size);
        }

        PacketField lastPacket = null;
        for(PacketField p : super.asArrayFields()){
            RefBuilder deserRef = new BitmaskRef(desRef, new ArgRef("22"));
            PacketField newPacket = new PacketField(p.getName(), p.getDesc(), Integer.class,deserRef , p.getSerializerMethod());
        fields.add(newPacket);
        lastPacket = p;
        }
        fields.removeLast();
        PacketField lP = new PacketField(lastPacket.getName(), lastPacket.getDesc(), Integer.class,new BitmaskRef(lastDesRef , new ArgRef("23")), lastPacket.getSerializerMethod());
        fields.add(lP);
        return fields;
    }

    @Override
    public Flattenable clone() {
        return new BitfieldBuildable((BitfieldField[]) super.buildables);
    }
}
