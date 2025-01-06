package Serializables;

import Serializables.Complex.Container;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import Serializables.Complex.*;
import Serializables.Types.PrimitiveMapper;

import java.lang.reflect.Type;
import java.util.*;

public class ProtMapper2 {
    private static final Map<String, ComplexType> types = new HashMap<>();
    private final Map<String, Map<String, Map<String, PacketV2>>> packets = new LinkedHashMap<>();
    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
       // this.types = types;
        /*
        for(Map.Entry<String, Object> type : types.entrySet()) {
            //if nonNative
            if (!type.getValue().equals("native") ) {
                this.types.put(type.getKey(), createComplexTypeFromJsonObject(type.getValue()));


            }
            //native
            else {
                try {
                    this.types.put(type.getKey(), new SelfClassableType(PrimitiveMapper.getClassOrException(type.getKey())));
                } catch (Throwable e) {
                    System.out.println("ERROR: Could not load native class " + type.getKey());
                }
            }

        }*/
    }


    public static ComplexType createComplexTypeFromJsonObject(Object type) {
        switch (type) {
            //object (protocolType or anon switch)
            case Map m: {
                if(m.get("name") == null){
                    return new Unsup("no name in protmap2 switch");
                }
                return createComplexTypeFromJsonObject(m.get("type"));
            }
            //complex container, switch, arr
            case List l: {
                String complexName = (String) l.getFirst();
                Object typeJson = l.get(1);
                switch (complexName) {
                    case "container":
                        return containerFromMappedJson((List<Map<String, Object>>) typeJson);
                    case "array":
                        return arrayFromMappedJson((Map<String, Object>) typeJson);
                    case "buffer":
             //           return bufferFromMappedJson((Map<String, Object>) typeJson);
                    default:
                        final String RESET = "\u001B[0m";
                        final String RED = "\u001B[34m";
                        return new UnsupComplexMultiple(RED + complexName + RESET);
                }
            }
            case String s: {
                if(types.get(s) != null && !types.get(s).equals("native"))
                    return types.get(s);

                return fieldFromMappedJson(s);

            }
            default:
                throw new UnexpectedJsonFormatException(type);
        }
    }

    private static Container containerFromMappedJson(List<Map<String, Object>> maps){
        List<ComplexType> subFields = new ArrayList<>();
        for(Map<String, Object> field : maps){
            subFields.add(createComplexTypeFromJsonObject(field));
        }
        return new Container(subFields);
    }
    private static Array arrayFromMappedJson(Map<String,Object> map){
        ComplexType type = createComplexTypeFromJsonObject(map.get("type"));
        if(map.get("countType") == null){
            //count is a field unsupported currently
            return new Array(ProtocolType.class, type);
        }
        //should be field so i get[0]
        TypeDescriptor countType = createComplexTypeFromJsonObject(map.get("countType")).getBuilder().getFirst();
        return new Array((Class<ProtocolType>) countType.getType(), type);
    }

    private static SelfClassableType fieldFromMappedJson(String s){

       //     return new Field(PrimSC.getClazz(string));
            try {
                return new SelfClassableType(PrimitiveMapper.getClassOrException(s), s);
            } catch (Throwable ex ) {
                 final String RESET = "\u001B[0m";
                 final String RED = "\u001B[31m";
                    return new SelfClassableType(Object.class, RED + "UNRPD_" + s + RESET);
            }
    }

    public Map<String, ComplexType> getTypes() {
        return types;
    }

    private void forEachPacket(String name, Object contents, String state, String target){
       @SuppressWarnings("unchecked")
        Object fields = ((ArrayList<Object>) contents).getLast();
       List<String> fieldClassesName = new ArrayList<>();

       ComplexType t =createComplexTypeFromJsonObject(contents);


       PacketV2 p = new PacketV2(name, t );
        packets.get(state).get(target).put(name, p);
    }
    @JsonAnySetter
    public void addPacket(String key, Object value) {
        // Any JSON key that isn't "types" will be treated as a packet
        //to server to client
        if (!packets.containsKey(key))
            packets.put(key, new LinkedHashMap<>());
        if (!(value instanceof Map<?, ?> toMap)) {
            throw new UnexpectedJsonFormatException(key);
        }
        //for toClient / toPacket
        for (Map.Entry<?, ?> subMap : toMap.entrySet()) {

            if (!packets.get(key).containsKey(subMap.getKey())) {
                packets.get(key).put(subMap.getKey().toString(), new LinkedHashMap<>());
            }
            if (!(subMap.getValue() instanceof Map<?, ?> destMap)) {
                throw new RuntimeException("Unsupported format for packet: " + subMap.getValue());
            }

            Map<?, ?> packetMap = (Map<?, ?>) destMap.get("types");
            for (Map.Entry<?, ?> specificPacketMap : packetMap.entrySet()) {
                String packetName = specificPacketMap.getKey().toString();
                Object contents = specificPacketMap.getValue();
                // PacketV2 p = new PacketV2(packetName, createComplexTypeFromJsonObject(packetType));
                //todo improve this
                if (packetName.equals("packet")) {
                     /*     //  packets.get(key).get(subMap.getKey()).put(p.getName(), p);
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
                            }*/
                } else {
                    //    packets.get(key).get(subMap.getKey()).put(p.getName(), p);
                    forEachPacket(packetName, contents, key,(String)subMap.getKey());
                }

            }
        }
    }


    public List<PacketV2> getPackets() {
        return packets.values().stream()
                .flatMap(level1 -> level1.values().stream())  // Flatten to second-level maps
                .flatMap(level2 -> level2.values().stream())  // Flatten to Packet objects
                .toList();                                   // Collect into a list
    }

}
