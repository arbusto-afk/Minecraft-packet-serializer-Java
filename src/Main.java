import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import neoutil.Packet;
import neoutil.PacketField;
import neoutil.Protocol;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.List;
import java.util.Map;
/*
     class PacketPrinter {

        public static void printPacket(String name, Object packetDef, String indent) {
            System.out.println(indent + "Packet: " + name);

            if (packetDef instanceof List) {
                List<?> packetList = (List<?>) packetDef;

                // Check if it's a container
                if (packetList.size() > 0 && "container".equals(packetList.get(0))) {
                    // Extract fields from the container
                    if (packetList.size() > 1 && packetList.get(1) instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> fields = (List<Map<String, Object>>) packetList.get(1);
                        if (fields.isEmpty()) {
                            System.out.println(indent + "  (no fields)");
                        } else {
                            for (Map<String, Object> field : fields) {
                                String fieldName = (String) field.get("name");
                                Object fieldType = field.get("type");
                                System.out.println(indent + "  " + fieldName + ": " + formatType(fieldType, indent + "    "));
                            }
                        }
                    }
                } else {
                    System.out.println(indent + "  (unknown structure)");
                }
            } else {
                System.out.println(indent + "  (invalid packet definition)");
            }
        }

         private static String formatType(Object typeDef, String indent) {
             if (typeDef instanceof String) {
                 return typeDef.toString(); // Simple type
             } else if (typeDef instanceof List) {
                 List<?> typeList = (List<?>) typeDef;

                 if (!typeList.isEmpty() && "mapper".equals(typeList.get(0))) {
                     // Handle mapper type
                     @SuppressWarnings("unchecked")
                     Map<String, Object> mapperDetails = (Map<String, Object>) typeList.get(1);
                     String baseType = (String) mapperDetails.get("type");
                     Map<?, ?> mappings = (Map<?, ?>) mapperDetails.get("mappings");
                     return "mapper<" + baseType + "> " + mappings;
                 } else if (!typeList.isEmpty() && "switch".equals(typeList.get(0))) {
                     // Handle switch type
                     @SuppressWarnings("unchecked")
                     Map<String, Object> switchDetails = (Map<String, Object>) typeList.get(1);
                     String compareTo = (String) switchDetails.get("compareTo");
                     @SuppressWarnings("unchecked")
                     Map<String, Object> fields = (Map<String, Object>) switchDetails.get("fields");
                     StringBuilder switchOutput = new StringBuilder("switch (compareTo=" + compareTo + ") { ");
                     for (Map.Entry<String, Object> fieldEntry : fields.entrySet()) {
                         switchOutput.append("case ").append(fieldEntry.getKey()).append(" -> ")
                                 .append(formatType(fieldEntry.getValue(), indent + "  "));
                     }
                     switchOutput.append(" }");
                     return switchOutput.toString();
                 } else {
                     // Handle arrays or other nested lists
                     String baseType = formatType(typeList.get(0), indent);
                     boolean isLengthPrefixed = typeList.size() > 1 && typeList.get(1) instanceof Map && ((Map<?, ?>) typeList.get(1)).containsKey("countType");
                     return "array<" + baseType + ">(" + (isLengthPrefixed ? "length-prefixed" : "non-length-prefixed") + ")";
                 }
             } else if (typeDef instanceof Map) {
                 @SuppressWarnings("unchecked")
                 Map<String, Object> typeMap = (Map<String, Object>) typeDef;

                 if (typeMap.containsKey("type") && typeMap.containsKey("countType")) {
                     // Handle buffer or arrays with countType
                     String baseType = formatType(typeMap.get("type"), indent);
                     return "array<" + baseType + ">(length-prefixed)";
                 } else {
                     return typeMap.toString(); // Handle raw map structures if needed
                 }
             }
             return "unknown";
         }
     }
*/

import java.util.*;

class PacketPrinter {
    private final Map<String, Packet> packetMap = new HashMap<>();

    public void printPacket(String name, Object packetDef, String indent) {
        System.out.println(indent + "Processing Packet: " + name);

        if (!(packetDef instanceof List))
            throw new RuntimeException("Bad packet format, is not arr");
        List<?> packetList = (List<?>) packetDef;

        if (packetList.isEmpty())
            throw new RuntimeException("Bad packet format, is empty");

        Packet packet = new Packet(name);it

        if (packetList.size() > 1 && packetList.get(1) instanceof List) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> fields = (List<Map<String, Object>>) packetList.get(1);
            for (Map<String, Object> field : fields) {
                String fieldName = (String) field.get("name");
                Object fieldType = field.get("type");
                PacketField packetField = new PacketField(fieldName, formatType(fieldType, indent + "    "));
                packet.addField(packetField);
            }


            packetMap.put(name, packet);

        }
    }



    public void logPackets() {
        System.out.println("Processed Packets:");
        for (Map.Entry<String, Packet> entry : packetMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private String formatType(Object typeDef, String indent) {
        if (typeDef instanceof String) {
            return (String) typeDef; // Simple type
        } else if (typeDef instanceof List) {
            List<?> typeList = (List<?>) typeDef;

            if (!typeList.isEmpty()) {
                String typeKeyword = (String) typeList.get(0);

                switch (typeKeyword) {
                    case "mapper": {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> mapperDetails = (Map<String, Object>) typeList.get(1);
                        String baseType = (String) mapperDetails.get("type");
                        Map<?, ?> mappings = (Map<?, ?>) mapperDetails.get("mappings");
                        return "mapper<" + baseType + "> " + mappings;
                    }
                    case "switch": {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> switchDetails = (Map<String, Object>) typeList.get(1);
                        String compareTo = (String) switchDetails.get("compareTo");
                        @SuppressWarnings("unchecked")
                        Map<String, Object> fields = (Map<String, Object>) switchDetails.get("fields");
                        StringBuilder switchOutput = new StringBuilder("switch (compareTo=" + compareTo + ") { ");
                        for (Map.Entry<String, Object> fieldEntry : fields.entrySet()) {
                            switchOutput.append("case ").append(fieldEntry.getKey()).append(" -> ")
                                    .append(formatType(fieldEntry.getValue(), indent + "  "));
                        }
                        if (switchDetails.containsKey("default")) {
                            switchOutput.append("default -> ").append(formatType(switchDetails.get("default"), indent + "  "));
                        }
                        switchOutput.append(" }");
                        return switchOutput.toString();
                    }
                    case "option": {
                        Object innerType = typeList.get(1);
                        return "(option)" + formatType(innerType, indent); // Correctly format option type
                    }
                    case "array": {
                        // Process nested arrays without redundant wrapping
                        Object innerType = typeList.get(1);
                        String formattedInnerType = formatType(innerType, indent);
                        if (formattedInnerType.startsWith("array<")) {
                            // Avoid double wrapping arrays
                            return formattedInnerType;
                        }
                        boolean isLengthPrefixed = innerType instanceof Map && ((Map<?, ?>) innerType).containsKey("countType");
                        return "array<" + formattedInnerType + ">(" + (isLengthPrefixed ? "length-prefixed" : "non-length-prefixed") + ")";
                    }
                    case "container": {
                        if (typeList.size() > 1 && typeList.get(1) instanceof List) {
                            @SuppressWarnings("unchecked")
                            List<Map<String, Object>> containerFields = (List<Map<String, Object>>) typeList.get(1);
                            StringBuilder containerOutput = new StringBuilder("container { ");
                            for (Map<String, Object> field : containerFields) {
                                String fieldName = (String) field.get("name");
                                Object fieldType = field.get("type");
                                containerOutput.append("\n").append(indent).append("  ").append(fieldName)
                                        .append(": ").append(formatType(fieldType, indent + "  "));
                            }
                            containerOutput.append("\n").append(indent).append("}");
                            return containerOutput.toString();
                        }
                    }
                    default: {
                        return typeKeyword + "<unknown>";
                    }
                }
            }
        } else if (typeDef instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> typeMap = (Map<String, Object>) typeDef;

            if (typeMap.containsKey("type") && typeMap.containsKey("countType")) {
                String baseType = formatType(typeMap.get("type"), indent);
                return "array<" + baseType + ">(length-prefixed)";
            } else {
                return typeMap.toString(); // Handle raw map structures if needed
            }
        }

        return "unknown";
    }

}


public class Main {
    public static void main(String[] args) throws IOException {
        String version = "1.21.3";
        String pcOrBedrock = "pc";
        List<Packet> packets = new ArrayList<>();
        PacketPrinter pp = new PacketPrinter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Protocol protocol = mapper.readValue(new File("minecraft-data/data/" + pcOrBedrock + "/" + version + "/protocol.json"), Protocol.class);

            // Print types
            /*
            System.out.println("Loaded types:");
            for (Map.Entry<String, Object> entry : protocol.getTypes().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            */
            // Print packets

            System.out.println("\nLoaded packets:");
            for (Map.Entry<String, Object> phaseEntry : protocol.getPackets().entrySet()) {
                String phase = phaseEntry.getKey();
                Object phaseData = phaseEntry.getValue();
                System.out.println("Phase: " + phase);

                if (phaseData instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> phaseMap = (Map<String, Object>) phaseData;

                    for (Map.Entry<String, Object> directionEntry : phaseMap.entrySet()) {
                        String direction = directionEntry.getKey();
                        Object directionData = directionEntry.getValue();
                        System.out.println("  Direction: " + direction);

                        if (directionData instanceof Map) {

                            //asumo que toClient y toServer tienen un solo hijo "types"
                            @SuppressWarnings("unchecked")
                            Map<String, Object> packetMap = (Map<String, Object>) ((LinkedHashMap) directionData).get("types");

                            for (Map.Entry<String, Object> packetEntry : packetMap.entrySet()) {
                                //    if(packetEntry instanceof Map) {

                                //  @SuppressWarnings("unchecked")
                                //    Map<String, Object> pMap = (Map<String, Object>)packetEntry;
                                //        for (Map.Entry<String, Object> pEntry: pMap.entrySet()) {
                                // Debugging raw packet definitions
                                String packetName = packetEntry.getKey();
                                Object packetDef = packetEntry.getValue();
                                //System.out.println("Debugging packetDef for " + packetName + ": " + packetDef);
                                pp.printPacket(packetEntry.getKey(), packetEntry.getValue(), "  ");

                                //   System.out.println("    Packet: " + packetEntry.getKey() + " -> " + packetEntry.getValue());
                                //         Packet p = new Packet();
                                //       p.addField((List)packetEntry.getValue());
                                //     packets.add(p);
                                //      }
                            }

                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        pp.logPackets();
    }
}


