package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

// VarInt Type
public class VarInt implements ProtocolType {
    private final int value;

    public VarInt(int value) {
        this.value = value;
    }

    public int asInt() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(5);
        int tempValue = value;
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
        int tempValue = value;
        while ((tempValue & ~0x7F) != 0) {
            buffer.put((byte) ((tempValue & 0x7F) | 0x80));
            tempValue >>>= 7;
        }
        buffer.put((byte) (tempValue & 0x7F));
    }

    public static VarInt readFromBuffer(ByteBuffer buffer) {
        int result = 0;
        int shift = 0;
        byte currentByte;
        do {
            currentByte = buffer.get();
            result |= (currentByte & 0x7F) << shift;
            shift += 7;
            if (shift > 35) {
                throw new IllegalArgumentException("VarInt is too big.");
            }
        } while ((currentByte & 0x80) != 0);
        return new VarInt(result);
    }
}
