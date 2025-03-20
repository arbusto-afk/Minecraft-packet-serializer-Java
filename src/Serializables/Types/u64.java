package Serializables.Types;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class u64 implements ProtocolType {
    private final BigInteger value;

    public u64(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) < 0 || value.compareTo(BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TWO).add(BigInteger.ONE)) > 0) {
            throw new IllegalArgumentException("Value must be between 0 and 2^64 - 1, but was: " + value);
        }
        this.value = value;
    }

    public BigInteger asBigInteger() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(value.longValue());  // Store as long
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putLong(value.longValue());
    }

    public static u64 readFrom(ByteBuffer buffer) {
        long longValue = buffer.getLong();
        return new u64(longValue >= 0 ? BigInteger.valueOf(longValue) : BigInteger.valueOf(longValue).add(BigInteger.ONE.shiftLeft(64)));
    }
}
