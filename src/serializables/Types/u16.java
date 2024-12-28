package serializables.Types;

import serializables.ProtocolType;

import java.nio.ByteBuffer;

public class u16 implements ProtocolType {
    private final int value;

    public u16(int value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("Value must be between 0 and 65535, but was: " + value);
        }
        this.value = value;
    }

    public int asInteger() {
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
