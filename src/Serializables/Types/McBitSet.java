package Serializables.Types;

import java.nio.ByteBuffer;
import java.util.BitSet;

public class McBitSet extends BitSet implements ProtocolType {
    public McBitSet() {
        super();
    }

    public McBitSet(int size) {
        super(size);
    }

    public McBitSet(byte[] bytes) {
        super.valueOf(bytes);
    }

    public McBitSet(long[] longs) {
        super.valueOf(longs);
    }
    @Override
    public byte[] serialize() {
        byte[] data = toByteArray();
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES + data.length);
        buffer.putInt(length()); // Store the bit length
        buffer.put(data);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putInt(length());
        buffer.put(toByteArray());
    }

    public static McBitSet deserialize(ByteBuffer buffer) {
        int bitLength = buffer.getInt();
        byte[] data = new byte[(bitLength + 7) / 8];
        buffer.get(data);
        McBitSet bitSet = new McBitSet();
        bitSet.or(BitSet.valueOf(data));
        return bitSet;
    }
}