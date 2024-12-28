package serializables.Types;
import java.nio.ByteBuffer;
import serializables.ProtocolType;

// Floating-Point Types
public class f32 implements ProtocolType {
    private final float value;

    public f32(float value) {
        this.value = value;
    }

    public float asFloat() {
        return value;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putFloat(value);
        return buffer.array();
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        buffer.putFloat(value);
    }
}

