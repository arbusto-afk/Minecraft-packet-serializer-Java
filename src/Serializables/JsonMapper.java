package Serializables;

import Serializables.Types.FlattenableBuilder;
import Serializables.Refactor.*;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class JsonMapper {

    private static final Map<String, Flattenable> newTypes = new LinkedHashMap<>();
    private static final Map<String, Map<String, Map<String, PacketV2>>> packets = new LinkedHashMap<>();
    private static final Multimap<String, PacketV2> totalPackets = ArrayListMultimap.create();

    public final static Set<String> unbuildableTypes = new LinkedHashSet<>();
    public final static List<String> unbuildableBehaviourals = new ArrayList<>();

    private static String vString = "";
    private static Path outputPath = Paths.get("output.java");
    final static String enumClassName = "PacketIDs";
    //<packetName, hexa string>
    final static Map<String, String> packetIdMap = new LinkedHashMap<>();
    static {
        FlattenableBuilder.setKnownTypes(newTypes);
    }

    public static void setVersion(String version) {
        JsonMapper.vString = version.replace(".", "_");
        outputPath = Paths.get("Protocol_" + vString + ".java");
        outputBuilder = new StringBuilder(
                "import java.math.BigInteger;\n" +
                        "import java.nio.ByteBuffer;\n" +
                        "import java.util.BitSet;\n" +
                        "import java.util.Map;\n" +
                        "import java.nio.ByteBuffer;\nimport Serializables.*;\nimport Serializables.Types.*;\n" +
                "import static Serializables.Types.Tuples.Tuples.*;\n" + "public class Protocol_"+vString + " {\n");
    }


    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {

       // newTypes.put("restBuffer", new RestBufferBuildable());
        //load types defined in primitiveMapper
        for(NativeTypesEnum p : NativeTypesEnum.values()) {
            newTypes.put(p.getName(), p.getClassBuildable());
        }
        //for each type
        for(Map.Entry<String, Object> entry : types.entrySet()) {
            if(!entry.getKey().matches("pstring|string")) {
                try {
                    //if its a valid flattenable
                    FlattenableBuilder.valueOf(entry.getKey().toUpperCase());
                    //do nothing
                } catch (IllegalArgumentException e) {
                    try {
                        Flattenable f;
                        if (entry.getValue() instanceof String s && s.equals("native")) {
                            f = FlattenableBuilder.createFlattenable(entry.getKey(), false);
                        } else {
                            f = FlattenableBuilder.createFlattenable(entry.getValue(), false);
                        }
                        newTypes.put(entry.getKey(), f);
                    } catch (Exception ex) {
                        unbuildableTypes.add(entry.getKey());
                    }
                }
            } else {
                newTypes.put("string", NativeTypesEnum.STRING.getClassBuildable());
            }
        }
    }

    public Map<String, Flattenable> getTypes() {
        return newTypes;
    }
    public Map<String, Map<String, Map<String, PacketV2>>> getPackets() {
        return packets;
    }
    public Multimap<String, PacketV2> getTotalPackets() { return totalPackets; }


    private final static Map<PacketV2, String> packetNameToClassString = new LinkedHashMap<>();
    private static StringBuilder outputBuilder = new StringBuilder(
            "\nimport java.nio.ByteBuffer;\nimport Serializables.*;\nimport Serializables.Types.*;\n" +
            "import static Serializables.Types.Tuples.Tuples.*;\n" + "public class Protocol_"+vString + " {\n");
    private void forEachPacket(String name, Object contents, String state, String target) {


        boolean stackName = name.startsWith("packet");
        Flattenable packetTypeContent = FlattenableBuilder.createFlattenable(contents, stackName);
        PacketV2 p = new PacketV2(name, packetTypeContent);
        packets.get(state).get(target).put(name, p);
        newTypes.put(name, packetTypeContent);
        totalPackets.put(name, p);
   //     System.out.println("Loaded packet: " + name + " -> <" + Arrays.toString(p.getFields()) + ">");
        if(name.startsWith("packet")) {
            final String enumidName = state + "_" + target + "_" + name;

            List<PacketField> fields = p.getFields().asPacketFields();
            StringBuilder fileContent = new StringBuilder("static class ").append(name).append(" extends PacketBase{\n");
            StringBuilder constructor = new StringBuilder("public " + name + "(\n");
            StringBuilder constructorAssignments = new StringBuilder();

            StringBuilder deserializerMethod = new StringBuilder("public static "+ name + " readFrom(ByteBuffer " + Consts.BUFNAME + "){\n");
            StringBuilder deserializerMethodPacketCreation = new StringBuilder("return new " + name + "(\n");
            for(PacketField field : fields){
                fileContent.append((field.getDesc().isEmpty() ? "" : "//" + field.getDesc() + "\n") + field.getSsrb().getCompleteRef() + " " + field.getName() + ";\n");
                constructor.append(field.getSsrb().getCompleteRef() + " " + field.getName() + ",\n");
                constructorAssignments.append("this." + field.getName() + " = " + field.getName() + ";\n");
                deserializerMethod.append(field.getSsrb().getCompleteRef() + " " + field.getName() + " = " +
                        field.getDeserializerMethod()
                        + ";\n");
                deserializerMethodPacketCreation.append(field.getName() + ",\n");
            }
            if(!fields.isEmpty()) {
                constructor.replace(constructor.length() - 2, constructor.length(), "").append("\n){\n");
                deserializerMethodPacketCreation.replace(deserializerMethodPacketCreation.length() - 2, deserializerMethodPacketCreation.length(), "").append("\n);\n");
            }
            else {
                constructor.append("){\n");
                deserializerMethodPacketCreation.append("\n);\n");
            }

            final String arrStr = "\t\tsuper(" + enumClassName + "." + enumidName +".getId());\n";
            //p.getFields().length != 0




            //One for closing constructor other for closing packet class
            String classEnd = "}\n";
            String packetClass = fileContent.toString() + constructor +  arrStr + constructorAssignments + classEnd + deserializerMethod + deserializerMethodPacketCreation + classEnd + classEnd;
            packetNameToClassString.put(p, packetClass);
        }
    }
    public static String indentString(String input) {
        StringBuilder result = new StringBuilder();
        int indentLevel = 0;
        boolean increaseIndent = false;

        for (String line : input.split("\n", -1)) { // Preserve empty lines
            String trimmedLine = line.stripLeading();

            // Adjust indentation before appending
            if (trimmedLine.startsWith("}") || trimmedLine.startsWith("){")|| trimmedLine.startsWith(");")) {
                indentLevel = Math.max(0, indentLevel - 1);
            }

            result.append("\t".repeat(indentLevel)).append(trimmedLine).append("\n");

            // Increase indent for class and block openings
            increaseIndent = trimmedLine.matches("(class|static class|public class)\\s+\\w+.*\\{\\s+")
                    || trimmedLine.endsWith("{")
                    || trimmedLine.endsWith("(")
                    && !trimmedLine.equals("){");
            if (increaseIndent) {
                indentLevel++;
            }
        }

        return result.toString();
    }

    public static String sufixAux(String input) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        int index = 0;

        while ((index = input.indexOf("aux", index)) != -1) {
            result.append(input, 0, index).append("aux").append(count++);
            input = input.substring(index + 3);
            index = 0;
        }

        return result.append(input).toString();
    }

    public void generatePacketIdEnums(String version){

        final String start = "enum "+ enumClassName+" {\n";
        final String end = "\n}\n";
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : packetIdMap.entrySet()) {
            sb.append("\t").append(entry.getKey()).append("(").append(entry.getValue()).append("),\n");
        }
        int aux = sb.lastIndexOf(",");
        if(aux != -1) {
            sb.setCharAt(aux, ';');
        }
        final String customLogic = "\n\tprivate final int id;\n\t" + enumClassName + "(int id){" +
                "\n\t\tthis.id = id;" +
                "\n\t}" +
                "\n\tpublic int getId(){" +
                "\n\t\treturn this.id;" +
                //+1 } for protocol whole class end
                "\n\t}\n}";

        for(String state : packets.keySet()){
            Map<String, Map<String, PacketV2>> directions = packets.get(state);
            outputBuilder.append("public static class " + state + "{\n");
            for(String dir : directions.keySet()) {
                Collection<PacketV2> packets = directions.get(dir).values();
                if (!packets.isEmpty()) {
                    outputBuilder.append("public static class " + dir + "{\n");
                    for (PacketV2 packet : packets) {
                        outputBuilder.append(packetNameToClassString.get(packet));
                    }
                    outputBuilder.append("}\n");
                }
            }
            outputBuilder.append("}\n");
        }

        outputBuilder.append(start).append(sb).append(customLogic).append(end);
        try {
            Files.write(outputPath, sufixAux(indentString(outputBuilder.toString())).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
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
              /*      Flattenable packetTypeContent = builder.newCreateBuildable(contents, newTypes);
                    PacketV2 p = new PacketV2(packetName, packetTypeContent);
                    Map<String, String> mapper =((MapperBuildable)(((ContainerField)(p.getFields().flatten()[0])).flatten()[0])).getPossibleValues();
                //    SwitchBuildable aux = ((SwitchBuildable)p.getFields()[1])
                    Map<String, Object> aux = (Map<String, Object>)((Map<String, Object>)((ArrayList)((Map<String, Object>)((ArrayList)((ArrayList)contents).get(1)).get(1)).get("type")).get(1)).get("fields");

                    for(Map.Entry<String, String> entry : mapper.entrySet()) {
                        String subPacketName = (String)(aux.get(entry.getValue()));
                        String subPacketId = entry.getKey();
                        finalMap.put(key + "_" + (String)subMap.getKey() + "_" + subPacketName, subPacketId);
                    }*/
                    Map<String, Object> nameMap = (Map<String, Object>)((Map)((List)((Map)((List)((List)contents).getLast()).getLast()).get("type")).getLast()).get("fields");
                    Map<String, Object> idMap = (Map<String, Object>) ((Map)((List)((Map)(((List)((List)contents).getLast()).getFirst())).get("type")).getLast()).get("mappings");
         //           int statePackets = 0;

                    Map<String, Integer> nameToIdMap = new LinkedHashMap<>();
                    for(Map.Entry<String, Object> entry : idMap.entrySet()) {
                       // int id = Integer.parseInt(entry.getKey().substring(2), 16);
                        String pName = key + "_" + (String)subMap.getKey() + "_" + (String)nameMap.get(((String)entry.getValue()));
                        packetIdMap.put(pName, entry.getKey());
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
