package Serializables.Types;
import Serializables.ProtocolType;
import Serializables.ProtocolType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class McString implements ProtocolType {
    private final String value;

    public McString(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

    @Override
    public byte[] serialize() {
        // Serialize the string length as a VarInt
        VarInt length = new VarInt(value.length());
        byte[] lengthBytes = length.serialize();

        // Serialize the string itself as UTF-8 bytes
        byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);

        // Combine the length and string bytes
        ByteBuffer buffer = ByteBuffer.allocate(lengthBytes.length + stringBytes.length);
        buffer.put(lengthBytes);
        buffer.put(stringBytes);

        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        // Serialize the string length as a VarInt
        VarInt length = new VarInt(value.length());
        length.serializeInto(buffer);

        // Serialize the string itself as UTF-8 bytes
        buffer.put(value.getBytes(StandardCharsets.UTF_8));
    }

    public static McString readFromBuffer(ByteBuffer buffer) {
        // Read the length prefix as a VarInt
        VarInt length = VarInt.readFrom(buffer);

        // Read the string bytes
        byte[] stringBytes = new byte[length.asInt()];
        buffer.get(stringBytes);

        // Convert the bytes to a string
        String value = new String(stringBytes, StandardCharsets.UTF_8);
        return new McString(value);
    }
}
