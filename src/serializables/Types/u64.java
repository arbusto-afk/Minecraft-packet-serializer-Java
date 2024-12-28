package serializables.Types;

import serializables.ProtocolType;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class u64 implements ProtocolType {
    private final long value;

    public u64(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must not be negative, but was: " + value);
        }
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
}


