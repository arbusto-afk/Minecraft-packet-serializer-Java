package neoutil;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Protocol {
    private Map<String, Object> types;
    private final List<Packet> ps = new ArrayList<>();

    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
        this.types = types;
    }

    public Map<String, Object> getTypes() {
        return types;
    }

    @JsonAnySetter
    public void addPacket(String key, Object value) {
        // Any JSON key that isn't "types" will be treated as a packet definition
        if (value instanceof Map<?, ?> toMap) {
            for (Map.Entry<?, ?> subMap : toMap.entrySet()) {
                if (!(subMap.getValue() instanceof Map<?, ?> destMap)) {
                    throw new RuntimeException("Unsupported format for packet: " + subMap.getValue());
                }
                Map<?, ?> packetMap = (Map<?, ?>) destMap.get("types");
                if (packetMap != null) {
                    for (Map.Entry<?, ?> specificPacketMap : packetMap.entrySet()) {
                        String packetName = specificPacketMap.getKey().toString();
                        Object packetType = specificPacketMap.getValue();
                        ps.add(new Packet(packetName, packetType)); // Uses the updated Packet constructor
                    }
                }
            }
        }
    }

    public List<Packet> getNewPackets() {
        return ps;
    }
}
