package Serializables;

import Serializables.Types.*;
import java.nio.ByteBuffer;


public class PacketBase {
    private final Object[] fields;
    private final Integer id;
    public PacketBase(Integer id, Object... fields){
        this.fields = fields;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private static final int TEMP_ALLOC_SIZE = 1024;
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(TEMP_ALLOC_SIZE);
        for (Object o : fields) {
            if (o instanceof ProtocolType pt) {
                pt.serializeInto(buffer);
            } else {
                throw new RuntimeException("Non serializable field for packet:" + o + ">>>>>" + this);
            }
        }
        return buffer.array();
    }
}
