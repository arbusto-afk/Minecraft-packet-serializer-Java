package Serializables;

import Serializables.Types.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.function.Function;


public abstract class PacketBase {
    private final boolean fieldsLocked;
    private Object[] fields;
    private final VarInt id;
    private final boolean lengthLocked;
    private VarInt length;
    public PacketBase(Integer id, Object... fields){
        this.fieldsLocked = true;
        this.fields = fields;
        this.lengthLocked = false;
        this.length = null;
        this.id = new VarInt(id);
    }
    public PacketBase(ByteBuffer buffer){
        this.fieldsLocked = false;
        this.lengthLocked = true;
        this.length = VarInt.readFrom(buffer);
        this.id = VarInt.readFrom(buffer);
        this.fields = null;
    }

    //used to set fields when creating a packet instance from a bytebuffer
    protected void setFields(Object... fields) {
        if(fieldsLocked)
            throw new IllegalStateException("PacketBase has already been locked");
        this.fields = fields;
    }

    //used to calculate length when creating a packet instance with parameters
    protected void setLength(VarInt length) {
        if(lengthLocked)
            throw new IllegalStateException("PacketBase has already been locked");
        this.length = length;
    }

    public Integer getId() {
        return id.asInt();
    }
    private static final Map<Integer, Function<ByteBuffer, ? extends PacketBase>> registry = new HashMap<>();

    public static void register(int id, Function<ByteBuffer, ? extends PacketBase> constructor) {
        registry.put(id, constructor);
    }
    public static PacketBase readPacket(int id, ByteBuffer in) throws IOException {
        Function<ByteBuffer, ? extends PacketBase> constructor = registry.get(id);
        return (constructor != null) ? constructor.apply(in) : null;
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
  //  public abstract byte[] deserialize(ByteBuffer buffer);
}
