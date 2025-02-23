package Serializables;

public class PacketBase {
    private final Object[] fields;
    public PacketBase(Object... fields){
        this.fields = fields;
    }
}
