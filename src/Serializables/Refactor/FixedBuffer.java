package Serializables.Refactor;

import Serializables.Consts;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.NotSelfPrefixedFuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.List;

public class FixedBuffer implements Flattenable{
    private int size;
    public FixedBuffer(int size) {
        if(size % 8 != 0){
            throw new IllegalArgumentException("Bitfield size must be a multiple of 8");
        }
        this.size = size;
    }

    @Override
    public List<PacketField> asArrayFields() {
        return this.asPacketFields();
    }

    @Override
    public List<PacketField> asPacketFields() {

        RefBuilder serRef = new ArgRef("NOSERIALIZER@FixedBuffer");
        RefBuilder desRef = new FuncRef("Bitset.valueOf", new NotSelfPrefixedFuncRef(Consts.BUFNAME.toString() + ".get", new ArgRef("new byte[" + size + "]")) );
        return List.of(new PacketField("", "", BitSet.class,desRef , serRef));
    }

    @Override
    public Flattenable clone() {
        return new FixedBuffer(this.size);
    }
}
