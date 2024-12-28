package neoutil;

import java.util.List;

public class ArrayField extends PacketField {
    private final PacketField countType;

    public ArrayField(String name, List<PacketField> type, PacketField countType) {
        super("array", name, type);
        this.countType = countType;
    }

    public PacketField getCountType() {
        return countType;
    }

    @Override
    public String toString() {
        return "ArrayField{name='" + getName() + "', type=" + getSubFields() + ", countType=" + countType + '}';
    }
}
