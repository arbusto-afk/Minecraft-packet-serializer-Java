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
    private static String version = "";
    private static String vString = "";
    private static Path outputPath = Paths.get("output.java");
    final static String enumClassName = "PacketIDs";
    public static void setVersion(String version) {
        JsonMapper.version = version;
        JsonMapper.vString = version.replace(".", "_");
        outputPath = Paths.get("Protocol_" + vString + ".java");
    }

    public final static List<String> unbuildableTypes = new ArrayList<>();

    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
        newTypes.put("void", new Flattenable[]{new ClassBuildable(Void.class)});
        newTypes.put("string", new Flattenable[]{new ClassBuildable(mcString.class)});
        newTypes.put("restBuffer", new Flattenable[]{new RestBufferBuildable()});
        //load types defined in primitiveMapper
        for(PrimitiveMapper p : PrimitiveMapper.values()){
            newTypes.put(p.name(), new Flattenable[]{new ClassBuildable(PrimitiveMapper.getClassOrException(p.name()))});
        }
        String[] exclusion = {"TopBitSetTerminatedArray","array", "buffer", "option", "bitfield", "container", "switch", "bitflags"};

        //for each type
        for(Map.Entry<String, Object> entry : types.entrySet()) {
            if(!newTypes.containsKey(entry.getKey()) && !Arrays.stream(exclusion).anyMatch(ex -> ex.equals(entry.getKey()))) {
                Flattenable[] type = builder.createTypeBuildable(entry.getValue());
                newTypes.put(entry.getKey(), type);
                boolean buildable = true;
                for(Flattenable f : type){
                    if(f.stringify("").contains("Object")){
                        buildable = false;
                        break;
                    }
                }
                if(!buildable){
//                    System.out.println("unbuildable " + entry.getKey());
                    unbuildableTypes.add(entry.getKey());
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
    private static String lastState = "";
    private static String lastTarget = "";
    private static boolean first = true;
    private static boolean aux = true;

    private final static StringBuilder outputBuilder = new StringBuilder();
    private void forEachPacket(String name, Object contents, String state, String target) {

        Flattenable[] packetTypeContent = builder.createBuildable(contents);
        PacketV2 p = new PacketV2(name, packetTypeContent);
        packets.get(state).get(target).put(name, p);
        newTypes.put(name, packetTypeContent);
        totalPackets.put(name, p);
   //     System.out.println("Loaded packet: " + name + " -> <" + Arrays.toString(p.getFields()) + ">");
        if(name.startsWith("packet")) {
            final String enumidName = state + "_" + target + "_" + name;
            final StringBuilder s = new StringBuilder();
            aux = true;
            if(!lastState.equals(state)){
                aux = false;
                lastState = state;
                if(first) {
                 first = false;
                    s.append("class Protocol { \n");
                } else {
                    s.append("}\n}\n");
                }
                s.append("\tstatic class " + state + " {\n");
            }
            if(!lastTarget.equals(target) && !first){
                lastTarget = target;
                if(aux) {
                    s.append("}\n");
                }
                s.append("\tstatic class " + target + " {\n");
            }
            s.append("static class ").append(name).append(" extends PacketBase{\n");

            final String arrStr = "\t\tsuper(" + enumClassName + "." + enumidName +".getId()" + (p.fieldNames().isEmpty() ? "" : ", ") + p.fieldNames() + ");\n";
            if(p.getFields().length != 0) {
                //create class variables
                for (String packetS : p.flattenAsString()) {
                    s.append("\t").append(packetS);
                }

                //append constructor
                s.append("\n\tpublic ").append(name).append("(");
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
                s.append("\n\tpublic ").append(name).append("(){\n").append(arrStr).append("\n\t}\n");

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
               // Path path = Paths.get(outputPath);
                if (test) {
                    outputBuilder.append("import Serializables.*;\nimport Serializables.Types.*;\n");
             //       Files.write(outputPath, "import Serializables.*;\nimport Serializables.Types.*;\n".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    test = false;
                }
                outputBuilder.append(s).append(classEnd);
              //  Files.write(outputPath, (indentString(s + classEnd)).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static String indentString(String input) {
        StringBuilder result = new StringBuilder();
        int indentLevel = 0;
        boolean increaseIndent = false;

        for (String line : input.split("\n", -1)) { // Preserve empty lines
            String trimmedLine = line.stripLeading();

            // Adjust indentation before appending
            if (trimmedLine.startsWith("}")) {
                indentLevel = Math.max(0, indentLevel - 1);
            }

            result.append("\t".repeat(indentLevel)).append(trimmedLine).append("\n");

            // Increase indent for class and block openings
            increaseIndent = trimmedLine.matches("(class|static class|public class)\\s+\\w+.*\\{\\s+") || trimmedLine.endsWith("{");
            if (increaseIndent) {
                indentLevel++;
            }
        }

        return result.toString();
    }
    static Map<String, String> finalMap = new LinkedHashMap<>();
    public void generatePacketIdEnums(String version){

        final String start = "enum "+ enumClassName+" {\n";
        final String end = "\n}\n";
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : finalMap.entrySet()) {
            sb.append("\t").append(entry.getKey()).append("(").append(entry.getValue()).append("),\n");
        }
        int aux = sb.lastIndexOf(",");
        sb.setCharAt(aux, ';');
        final String customLogic = "\n\tprivate final int id;\n\t" + enumClassName + "(int id){" +
                "\n\t\tthis.id = id;" +
                "\n\t}" +
                "\n\tpublic int getId(){" +
                "\n\t\treturn this.id;" +
                //+1 } for protocol whole class end
                "\n\t}\n}";
        outputBuilder.append(end + end + start).append(sb).append(customLogic).append(end);
        try {
            Files.write(outputPath, indentString(outputBuilder.toString()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                    Flattenable[] packetTypeContent = builder.createBuildable(contents);
                    PacketV2 p = new PacketV2(packetName, packetTypeContent);
                    Map<String, String> mapper = ((MapperBuildable)p.getFields()[0].flatten()[0]).getPossibleValues();
                //    SwitchBuildable aux = ((SwitchBuildable)p.getFields()[1])
                    Map<String, Object> aux = (Map<String, Object>)((Map<String, Object>)((ArrayList)((Map<String, Object>)((ArrayList)((ArrayList)contents).get(1)).get(1)).get("type")).get(1)).get("fields");

                    for(Map.Entry<String, String> entry : mapper.entrySet()) {
                        String subPacketName = (String)(aux.get(entry.getValue()));
                        String subPacketId = entry.getKey();
                        finalMap.put(key + "_" + (String)subMap.getKey() + "_" + subPacketName, subPacketId);
                    }
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
