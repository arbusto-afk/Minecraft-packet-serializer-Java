package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

public
class u16 implements ProtocolType {
    private final int value;

    public u16(int value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("Value must be between 0 and 65535, but was: " + value);
        }
        this.value = value;
    }

    public int asInt() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort((short) value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putShort((short) value);
    }

    public static u16 readFrom(ByteBuffer buffer) {
        return new u16(buffer.getShort() & 0xFFFF);
    }
}
