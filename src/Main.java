import java.io.File;
import java.io.IOException;

import neoutil.ProtocolMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import serializables.ProtMapper2;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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


public class Main {
    public static void main(String[] args) throws IOException {
        String version = "1.21.3";
        String pcOrBedrock = "pc";
        ObjectMapper mapper = new ObjectMapper();
        //ProtocolMapper protocol = mapper.readValue(new File("minecraft-data/data/" + pcOrBedrock + "/" + version + "/protocol.json"), ProtocolMapper.class);
        //protocol.getPackets().get(78).getClasses(protocol.getTypes());
        //System.out.println(protocol.getPackets());

       ProtMapper2 prot = mapper.readValue(new File("minecraft-data/data/" + pcOrBedrock + "/" + version + "/protocol.json"), ProtMapper2.class);
     //   System.out.println(prot.getPackets());
        System.out.println(prot.getPackets());
        //        System.out.println(protocol.getTypes());
        //varint, 32 , 32 ,32 ,16

        //65 explosion | vec3, optional vec3, particle, varint, switch
        //
    }
}


