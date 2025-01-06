package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

public class i32 implements ProtocolType {
    private final int value;

    public i32(int value) {
        this.value = value;
    }

    public int asInteger() {
        return value;
    }

    @Override
    public byte[] serialize() {
        return new byte[] {
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value
        };
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put((byte) (value >> 24));
        buffer.put((byte) (value >> 16));
        buffer.put((byte) (value >> 8));
        buffer.put((byte) value);
    }
}
