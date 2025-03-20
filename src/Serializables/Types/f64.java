package Serializables.Types;

import java.nio.ByteBuffer;

public class f64 implements ProtocolType {
    private final double value;

    public f64(double value) {
        this.value = value;
    }

    public double asDouble() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putDouble(value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putDouble(value);
    }

    public static f64 readFrom(ByteBuffer buffer) {
        return new f64(buffer.getDouble());
    }
}