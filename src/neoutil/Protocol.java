package neoutil;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

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

    private List<Packet> ps = new ArrayList<>();
    @JsonAnySetter
    public void addPacket(String key, Object value) {
        // Any JSON key that isn't "types" will go into the packets map.
     //   System.out.println("Top-level mapping: " + key + " -> " + value);
        if (value instanceof Map) {
            Map<?, ?> toMap = (Map<?, ?>) value;
            for(Map.Entry<?, ?> subMap : toMap.entrySet()){
                if(!(subMap.getValue() instanceof Map))
                    throw new RuntimeException(subMap.getValue() + "unsupported format");
                Map<?, ?> destMap = (Map<?,?>)subMap.getValue();
                Map<?, ?> packetMap = (Map<?, ?>) destMap.get("types");
                for(Map.Entry<?,?> specificPacketMap : packetMap.entrySet()){
                   // System.out.println("Found packet " + specificPacketMap.getKey()+ "->" + specificPacketMap.getValue());
                    //new Packet(specificPacketMap.getKey(), getFields(specificPacketMap.getValue()));
                    Packet p = new Packet(specificPacketMap.getKey().toString(), getFields(specificPacketMap.getValue()));
                    ps.add(p);
                }
            }
        }
        //packets.put(key, value);
    }
    private List<PacketField> getFields(Object o){
        switch(o){
            case List l: {
                if(l.size() > 2)
                    throw new RuntimeException("Unsup arr size");
                String arrTypeName = l.getFirst().toString();
                if(arrTypeName.contains("<sf"))
                    return l;
                Object type = l.get(1);
                switch(arrTypeName){
                    case "container": {
                        List<PacketField> fields = new ArrayList<>();
                        for(Object field : (List<Object>)type){
                            fields.addAll(getFields(field));
                        }
                        return fields;
                    }
                    case "array": {
                        var tmp = ((Map<String, Object>) type).get("countType");
                        if(tmp == null){
                            tmp = getFields(((Map<String, Object>) type).get("count"));
                        }
                        PacketField cType = getFields(tmp).getFirst();

                        List<PacketField> arrType = getFields(((Map<String,Object>) type).get("type"));
                        return List.of(new ArrayField("array", arrType, cType));
                    }
                    default: {
                        return List.of(new PacketField(arrTypeName, "asd"));
                    }
                }

            }
            case Map m: {
                String name = m.get("name") != null ? m.get("name").toString() : "anon";
                return List.of(new PacketField(getFields(m.get("type")), name));
            }
            case String s: {
                return List.of(new PacketField(s, "sf"));
            }
            default: {
                throw new RuntimeException("Unsupporetd format for: " + o);
            }
        }
    }

    public Map<String, Object> getPackets() {
        return packets;
    }

    public List<Packet> getNewPackets(){
        return ps;
    }
}
