package Serializables.Types;

import Serializables.ProtocolType;
import java.nio.*;
public class i8 implements ProtocolType {
    private final byte value;

    public i8(byte value) {
        this.value = value;
    }

    public byte asByte() {
        return value;
    }

    @Override
    public byte[] serialize() {
        return new byte[] { value };
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put(value);
    }
}

