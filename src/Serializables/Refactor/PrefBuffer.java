package Serializables.Refactor;

import Serializables.Consts;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.FlattenableBuilder;
import neoutil.Packet;

import java.util.BitSet;
import java.util.List;

public class PrefBuffer implements Flattenable{

    ClassBuildable countType;

    public PrefBuffer(ClassBuildable countType) {
        this.countType = countType;
    }

    @Override
    public Flattenable clone() {
        return new PrefBuffer(countType);
    }

    @Override
    public List<PacketField> asPacketFields() {
        RefBuilder serRef = new ArgRef("NOSERIALIZER@PrefBuffer");
        PacketField p = countType.asPacketFields().getFirst();
        RefBuilder desRef = new FuncRef("BitSet.valueOf", new FuncRef(Consts.BUFNAME.toString() + ".get", new ArgRef("new byte[" + p.getDeserializerMethod()  + "]")) );
        return List.of(new PacketField("", "", BitSet.class,desRef , serRef));
    }

    @Override
    public List<PacketField> asArrayFields() {
        return this.asPacketFields();
    }
}
