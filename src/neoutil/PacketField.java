package neoutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PacketField {
    private final String name;
    private final String type;
    private final List<PacketField> subFields;

    public PacketField(String type, String name, List<PacketField> subFields) {
        this.type = type;
        this.name = name;
        this.subFields = subFields;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<PacketField> getSubFields() {
        return subFields;
    }

    @Override
    public String toString() {
        return "<" + name + "(" + (type != null ? type : subFields) + ")>";
    }

}
