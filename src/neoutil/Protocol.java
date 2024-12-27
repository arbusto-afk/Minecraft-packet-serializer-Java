package neoutil;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Protocol {
    private Map<String, Object> types = new HashMap<>();
    private final Map<String, Object> packets = new HashMap<>();

    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
        this.types = types;
    }

    public Map<String, Object> getTypes() {
        return types;
    }

    @JsonAnySetter
    public void addPacket(String key, Object value) {
        // Any JSON key that isn't "types" will go into the packets map.
        packets.put(key, value);
    }

    public Map<String, Object> getPackets() {
        return packets;
    }
}
