package Serializables.Types;

import Serializables.ProtocolType;

import java.math.BigInteger;
import java.nio.ByteBuffer;

// UUID Type
public class UUID implements ProtocolType {
    private final BigInteger value;

    public UUID(BigInteger value) {
        if (value.signum() < 0 || value.bitLength() > 128) {
            throw new IllegalArgumentException("UUID value must be a 128-bit unsigned integer.");
        }
        this.value = value;
    }

    public BigInteger asBigInteger() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        byte[] bytes = value.toByteArray();
        if (bytes.length > 16) {
            throw new IllegalStateException("Value exceeds 128 bits.");
        }
        int padding = 16 - bytes.length;
        for (int i = 0; i < padding; i++) {
            buffer.put((byte) 0x00);
        }
        buffer.put(bytes);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        byte[] bytes = serialize();
        buffer.put(bytes);
    }

    public static UUID readFromBuffer(ByteBuffer buffer) {
        byte[] bytes = new byte[16];
        buffer.get(bytes);
        return new UUID(new BigInteger(1, bytes));
    }
}
