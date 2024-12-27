package neoutil;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private final String name;
    private final List<PacketField> fields = new ArrayList<>();

    public Packet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<PacketField> getFields() {
        return fields;
    }

    public void addField(PacketField field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Packet{name='" + name + "', fields=[");
        for (PacketField field : fields) {
            sb.append("\n  ").append(field);
        }
        sb.append("\n]}");
        return sb.toString();
    }
}
