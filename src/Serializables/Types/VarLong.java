package Serializables.Types;

import java.nio.ByteBuffer;

// VarLong Type
public class VarLong implements ProtocolType {
    private final long value;

    public VarLong(long value) {
        this.value = value;
    }

    public long asLong() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        long tempValue = value;
        while ((tempValue & ~0x7F) != 0) {
            buffer.put((byte) ((tempValue & 0x7F) | 0x80));
            tempValue >>>= 7;
        }
        buffer.put((byte) (tempValue & 0x7F));
        byte[] result = new byte[buffer.position()];
        buffer.flip();
        buffer.get(result);
        return result;
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        long tempValue = value;
        while ((tempValue & ~0x7F) != 0) {
            buffer.put((byte) ((tempValue & 0x7F) | 0x80));
            tempValue >>>= 7;
        }
        buffer.put((byte) (tempValue & 0x7F));
    }

    public static VarLong readFrom(ByteBuffer buffer) {
        long result = 0;
        int shift = 0;
        byte currentByte;
        do {
            currentByte = buffer.get();
            result |= (long) (currentByte & 0x7F) << shift;
            shift += 7;
            if (shift > 70) {
                throw new IllegalArgumentException("VarLong is too big.");
            }
        } while ((currentByte & 0x80) != 0);
        return new VarLong(result);
    }
}
