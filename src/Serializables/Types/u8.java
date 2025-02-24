package Serializables.Types;

import Serializables.ProtocolType;
import java.nio.*;
public
class u8 implements ProtocolType {
    private final short value;

    public u8(short value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Value must be between 0 and 255, but was: " + value);
        }
        this.value = value;
    }

    public short asShort() {
        return value;
    }

    @Override
    public byte[] serialize() {
        return new byte[]{(byte) value};
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put((byte) value);
    }

    public static u8 readFrom(ByteBuffer buffer) {
        return new u8((short) (buffer.get() & 0xFF));
    }
}