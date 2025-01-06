package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

public class u32 implements ProtocolType {
    private final long value;

    public u32(long value) {
        if (value < 0 || value > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Value must be between 0 and 4294967295, but was: " + value);
        }
        this.value = value;
    }

    public long asLong() {
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
