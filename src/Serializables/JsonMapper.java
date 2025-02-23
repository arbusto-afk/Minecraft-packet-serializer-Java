package Serializables;

import Serializables.Types.Void;
import Serializables.Refactor.*;
import Serializables.Types.mcString;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import Serializables.Types.PrimitiveMapper;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class JsonMapper {

    private static final Map<String, Flattenable[]> newTypes = new LinkedHashMap<>();
    private static final Map<String, Map<String, Map<String, PacketV2>>> packets = new LinkedHashMap<>();
    private static final Multimap<String, PacketV2> totalPackets = ArrayListMultimap.create();
    private static final JsonToBuildable builder = new JsonToBuildable(newTypes);
    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
        newTypes.put("void", new Flattenable[]{new ClassBuildable(Void.class)});
        newTypes.put("string", new Flattenable[]{new ClassBuildable(mcString.class)});
        newTypes.put("restBuffer", new Flattenable[]{new RestBufferBuildable()});
        for(PrimitiveMapper p : PrimitiveMapper.values()){
            this.newTypes.put(p.name(), new Flattenable[]{new ClassBuildable(PrimitiveMapper.getClassOrException(p.name()))});
        }
        String[] exclusion = {"array", "buffer", "option", "bitfield", "container", "switch", "bitflags"};
        for(Map.Entry<String, Object> entry : types.entrySet()) {
            if(!newTypes.containsKey(entry.getKey()) && !Arrays.stream(exclusion).anyMatch(ex -> ex.equals(entry.getKey()))) {
                newTypes.put(entry.getKey(), builder.createTypeBuildable(entry.getValue()));
                if((newTypes.get(entry.getKey()).toString().contains("Object") || newTypes.get(entry.getKey()).toString().contains("ComplexT")) && !Arrays.stream(exclusion).anyMatch(entry.getKey()::equals)) {
            //        System.err.println("Could not load type " + entry.getKey() + "-> " + newTypes.get(entry.getKey()));
                System.out.println("Unbuildable: " + entry.getKey());
                }
            }
        }
    }

    public Map<String, Flattenable[]> getTypes() {
        return newTypes;
    }
    public Map<String, Map<String, Map<String, PacketV2>>> getPackets() {
        return packets;
    }
    public Multimap<String, PacketV2> getTotalPackets() { return totalPackets; }


    private boolean test = true;
    private void forEachPacket(String name, Object contents, String state, String target) {
        Flattenable[] packetTypeContent = builder.createBuildable(contents);
        PacketV2 p = new PacketV2(name, packetTypeContent);
        packets.get(state).get(target).put(name, p);
        newTypes.put(name, packetTypeContent);
        totalPackets.put(name, p);
   //     System.out.println("Loaded packet: " + name + " -> <" + Arrays.toString(p.getFields()) + ">");
        if(name.startsWith("packet")) {
            final String className = state + "_" + target + "_" + name;
            final StringBuilder s = new StringBuilder().append("class ").append(className).append(" extends PacketBase{\n");

            final String arrStr = "\t\tsuper(" + p.fieldNames() + ");\n";
            if(p.getFields().length != 0) {
                //create class variables
                for (String packetS : p.flattenAsString()) {
                    s.append("\t").append(packetS);
                }

                //append constructor
                s.append("\n\tpublic ").append(className).append("(");
                //insert constructor fields
                StringBuilder temp = new StringBuilder("\n\t\t");
                for (String packetS : p.flattenAsString()) {
                    temp.append(packetS.replace(";", ",").replace("\n", "\n\t\t"));
                }
                if (temp.lastIndexOf(",\n") != -1) {
                    int lastComma = temp.lastIndexOf(",\n");
                    if (lastComma != -1) {
                        temp.deleteCharAt(lastComma);
                    }
                }
                if ( false && (p.fieldNames().split(",").length == 1 || p.fieldNames().split(",").length == 2) && !(p.fieldNames().contains("//"))) {
                    s.append(temp.toString().replace("\n", "").replace("\t", ""));
                } else {
                    s.append(temp.toString());
                }



                //close constructor args
                s.deleteCharAt(s.length() - 1);
                s.append("){\n");
                s.append(arrStr);


                //assign args to fields
                for (String subString : p.fieldNames().split(",")) {
                    if(!subString.isEmpty()) {
                        if (subString.startsWith(" ")) {
                            s.append("\t\tthis." + subString.substring(1) + " = " + subString + ";\n");
                        } else {
                            s.append("\t\tthis." + subString + " = " + subString + ";\n");
                        }
                    }
                }
                //close constructor
                s.append("\t}\n");
            }
            else {
          //      s.append("\n\tpublic ").append(className).append("(){\n").append(arrStr).append("\n\t}\n");

            }
//            String s2 = "" +
//            "\t\tByteBuffer bf = ByteBuffer.allocate(1024);\n"
//         +  " \t\tfor(Object o : packetFields){\n"
//             +  " \t\t\tif(o instanceof ProtocolType pt){\n"
//            +       "\t\t\t\tpt.serializeInto(bf);\n"
//           +   "  \t\t\t} else {\n"
//            +      "\t\t\t\tthrow new RuntimeException(\"Attempting to serialize non PT field\" + o);\n"
//            +    "\t\t\t}\n"
//           + "\t\t}\n"
//           + "\t\treturn bf.array();\n}\n";
            String classEnd = "}\n";

            try {
                Path path = Paths.get("output.java");
                if (test) {
                    Files.write(path, "import Serializables.*;\nimport Serializables.Types.*;\n".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    test = false;
                }
                Files.write(path, ((s + classEnd)).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
                            } aca cierro anidado pero no me deja asi que comento
                        */
                } else {

                    //    packets.get(key).get(subMap.getKey()).put(p.getName(), p);
                    forEachPacket(packetName, contents, key,(String)subMap.getKey());
                }

            }
        }
    }

/*
    public List<PacketV2> getPackets() {
        return packets.values().stream()
                .flatMap(level1 -> level1.values().stream())  // Flatten to second-level maps
                .flatMap(level2 -> level2.values().stream())  // Flatten to Packet objects
                .toList();                                   // Collect into a list
    }
    public Object buildAll(){
        Map<String,Object> arr = new LinkedHashMap<>();
        for(Map.Entry<String, TypeDescriptor> entry : types.entrySet()) {
            try {
                arr.put(entry.getKey(), entry.getValue().getBuilder());
            } catch (Exception e) {
                System.out.println("Erro|r building: " + entry.getKey());
            }
        }
        return arr;
    }

    public PacketV2 getPacket(String state, String clientboundOrServerbound, String name) {
        if(!packets.containsKey(state)) {
            throw new NoSuchElementException("State not found: " + state);
        }
        if(packets.get(state).get(clientboundOrServerbound).containsKey(name)) {
            throw new NoSuchElementException("packet '" + name +"' not found: ");
        }
        return packets.get(state).get(clientboundOrServerbound).get(name);
    }*/

}
