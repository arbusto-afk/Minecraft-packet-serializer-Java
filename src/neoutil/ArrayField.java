package neoutil;

import java.util.List;

public class ArrayField extends PacketField{
    PacketField cType;
    public ArrayField(String name, List<PacketField> type,PacketField countType) {
        super(type, name);
        this.cType = countType;
    }

    public ArrayField(String name, List<PacketField> type,PacketField countType, Boolean optional) {
        super(name, type, optional);
        this.cType = countType;
    }
}
