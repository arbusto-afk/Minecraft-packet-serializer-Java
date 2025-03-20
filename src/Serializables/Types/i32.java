package Serializables.Types;

import java.nio.ByteBuffer;

public class i32 implements ProtocolType {
    private final int value;

    public i32(int value) {
        this.value = value;
    }

    public int asInt() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putInt(value);
    }

    public static i32 readFrom(ByteBuffer buffer) {
        return new i32(buffer.getInt());
    }
}
