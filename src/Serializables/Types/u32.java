package Serializables.Types;

import java.nio.ByteBuffer;

public
class u32 implements ProtocolType {
    private final long value;

    public u32(long value) {
        if (value < 0 || value > 4294967295L) {
            throw new IllegalArgumentException("Value must be between 0 and 4294967295, but was: " + value);
        }
        this.value = value;
    }

    public long asLong() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt((int) value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putInt((int) value);
    }

    public static u32 readFrom(ByteBuffer buffer) {
        return new u32(buffer.getInt() & 0xFFFFFFFFL);
    }
}
