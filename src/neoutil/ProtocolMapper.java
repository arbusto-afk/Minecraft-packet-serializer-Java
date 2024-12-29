package neoutil;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ProtocolMapper {
    private Map<String, List<PacketField>> types;
    private final Map<String, Map<String, Map<String, Packet>>> packets = new LinkedHashMap<>();
    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
       // this.types = types;
        for(Map.Entry<String, Object> type : types.entrySet()){
            types.put(type.getKey(), PacketField.extractFields(type.getValue()));
        }
    }

    public Map<String, List<PacketField>> getTypes() {
        return types;
    }

    @JsonAnySetter
    public void addPacket(String key, Object value) {
        // Any JSON key that isn't "types" will be treated as a packet
        //to server to client
        if(!packets.containsKey(key))
            packets.put(key, new LinkedHashMap<>());
        if (value instanceof Map<?, ?> toMap) {
            for (Map.Entry<?, ?> subMap : toMap.entrySet()) {

                if(!packets.get(key).containsKey(subMap.getKey())){
                    packets.get(key).put(subMap.getKey().toString(), new LinkedHashMap<>());
                }
                if (!(subMap.getValue() instanceof Map<?, ?> destMap)) {
                    throw new RuntimeException("Unsupported format for packet: " + subMap.getValue());
                }
                Map<?, ?> packetMap = (Map<?, ?>) destMap.get("types");
                if (packetMap != null) {
                    for (Map.Entry<?, ?> specificPacketMap : packetMap.entrySet()) {
                        String packetName = specificPacketMap.getKey().toString();
                        Object packetType = specificPacketMap.getValue();
                        Packet p = new Packet(packetName, packetType);
                        //todo improve this
                        if(packetName.equals("packet")){
                          //  packets.get(key).get(subMap.getKey()).put(p.getName(), p);
                            String mapper = p.getFields().get(0).getType();
                            String regex = "0x([0-9a-fA-F]+)=([\\w_]+)";
                            Pattern pattern = Pattern.compile(regex);

                            // Process each input string
                            for (String input : Arrays.asList(mapper)) {
                                Matcher matcher = pattern.matcher(input);
                                //  System.out.println("Mappings:");
                                while (matcher.find()) {
                                    String id = "0x" + matcher.group(1);
                                    String name = matcher.group(2);
                                    //    System.out.println("ID: " + id + ", Name: " + name);
                                    if (packets.get(key).get(subMap.getKey()).get("packet_" + name) != null) {
                                        packets.get(key).get(subMap.getKey()).get("packet_" + name).setId(Integer.decode(id));
                                    }
                                }
                                //     System.out.println();
                            }
                        }
                        else {
                            packets.get(key).get(subMap.getKey()).put(p.getName(), p);
                        }
                    }
                }
            }
        }
    }

    public List<Packet> getPackets() {
        return packets.values().stream()
                .flatMap(level1 -> level1.values().stream())  // Flatten to second-level maps
                .flatMap(level2 -> level2.values().stream())  // Flatten to Packet objects
                .toList();                                   // Collect into a list
    }

}
