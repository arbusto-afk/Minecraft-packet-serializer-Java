package Serializables.Types;

import Serializables.ProtocolType;
import java.nio.*;
public class u8 implements ProtocolType {
    private final int value;

    public u8(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Value must be between 0 and 255, but was: " + value);
        }
        this.value = value;
    }

    public int asInteger() {
        return value;
    }

    @Override
    public byte[] serialize() {
        return new byte[] { (byte) value };
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put((byte) value);
    }
}

