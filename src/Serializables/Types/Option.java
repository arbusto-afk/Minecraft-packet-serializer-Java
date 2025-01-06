package Serializables.Types;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;

// Option Type
public class Option<T extends ProtocolType> implements ProtocolType {
    private final boolean present;
    private final T value;

    public Option(boolean present, T value) {
        if (present && value == null) {
            throw new IllegalArgumentException("Value cannot be null if present is true.");
        }
        this.present = present;
        this.value = value;
    }

    public boolean isPresent() {
        return present;
    }

    public T getValue() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(present ? 1 + value.serialize().length : 1);
        buffer.put((byte) (present ? 0x01 : 0x00));
        if (present) {
            buffer.put(value.serialize());
        }
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.put((byte) (present ? 0x01 : 0x00));
        if (present) {
            value.serializeInto(buffer);
        }
    }

    public static <T extends ProtocolType> Option<T> readFromBuffer(ByteBuffer buffer, java.util.function.Function<ByteBuffer, T> reader) {
        byte flag = buffer.get();
        if (flag == 0x00) {
            return new Option<>(false, null);
        } else if (flag == 0x01) {
            return new Option<>(true, reader.apply(buffer));
        } else {
            throw new IllegalArgumentException("Invalid Option flag: " + flag);
        }
    }
}
