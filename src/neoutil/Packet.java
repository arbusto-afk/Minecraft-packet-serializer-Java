package neoutil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Packet {
    private final String name;
    private final List<PacketField> fields;

    @JsonCreator
    public Packet(
            @JsonProperty("name") String name,
            @JsonProperty("type") Object type) {
        this.name = name;
        this.fields = PacketField.extractFields(type);
    }

    public String getName() {
        return name;
    }

    public List<PacketField> getFields() {
        return fields;
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
