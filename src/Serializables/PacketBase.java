package Serializables;

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
}
