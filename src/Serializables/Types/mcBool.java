package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

public class mcBool implements ProtocolType {
    private final boolean value;

    public mcBool(boolean value) {
        this.value = value;
    }

    public boolean asBoolean() {
        return value;
    }

    @Override
    public byte[] serialize() {
        return new byte[]{(byte) (value ? 0x01 : 0x00)};
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put((byte) (value ? 0x01 : 0x00));
    }

    public static mcBool readFrom(ByteBuffer buffer) {
        return new mcBool(buffer.get() != 0);
    }
}
