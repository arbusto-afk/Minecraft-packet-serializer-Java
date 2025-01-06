package Serializables.Types;

import Serializables.ProtocolType;

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
        return new byte[] {
                (byte) (value >> 8),
                (byte) value
        };
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put((byte) (value >> 8));
        buffer.put((byte) value);
    }
}
