package Serializables.Types;

import java.nio.ByteBuffer;

public class i16 implements ProtocolType {
    private final short value;

    public i16(short value) {
        this.value = value;
    }

    public short asShort() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putShort(value);
    }

    public static i16 readFrom(ByteBuffer buffer) {
        return new i16(buffer.getShort());
    }
}
