package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

public class i64 implements ProtocolType {
    private final long value;

    public i64(long value) {
        this.value = value;
    }

    public long asLong() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putLong(value);
    }


    public static i64 readFrom(ByteBuffer buffer) {
        return new i64(buffer.getLong());
    }
}
