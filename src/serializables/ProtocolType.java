package serializables;
import java.nio.ByteBuffer;

public interface ProtocolType {
        byte[] serialize();

        void serializeInto(ByteBuffer buffer);
}
