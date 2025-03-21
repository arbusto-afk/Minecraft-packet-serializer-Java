import java.io.IOException;

import Serializables.PacketBase;
import Serializables.ProtocolSerializer;

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
        String version = "1.21.1";
        String pcOrBedrock = "pc";
        ProtocolSerializer mapper = new ProtocolSerializer(version, pcOrBedrock);
       // var packet = Protocol.configuration_toClient_packet_feature_flags.getId();

     //   PacketBase p = new Protocol.play.toClient.packet_map_chunk();
       return;
        //varint, 32 , 32 ,32 ,16

        //65 explosion | vec3, optional vec3, particle, varint, switch
        //

    }

    private static void generateJavaSource(Class<?> clazz) throws Exception {
        StringBuilder source = new StringBuilder();

        // Generate the package statement
        String packageName = clazz.getPackageName();
        if (!packageName.isEmpty()) {
            source.append("package ").append(packageName).append(";\n\n");
        }

        // Start the class definition
        source.append("public class ").append(clazz.getSimpleName()).append(" {\n");

        // Add methods
        for (var method : clazz.getDeclaredMethods()) {
            source.append("    public ").append(method.getReturnType().getSimpleName())
                    .append(" ").append(method.getName()).append("() {\n")
                    .append("        return ").append("\"Hello, world!\";").append("\n") // Example logic
                    .append("    }\n");
        }

        // End the class definition
        source.append("}\n");

        // Write the source to a .java file
        String classFilePath = "src/" + packageName.replace('.', '/') + "/" + clazz.getSimpleName() + ".java";
        java.nio.file.Path path = java.nio.file.Paths.get(classFilePath);
        java.nio.file.Files.createDirectories(path.getParent());
        java.nio.file.Files.writeString(path, source.toString());

        System.out.println("Class source written to: " + classFilePath);
    }

    record asd(Integer a, Integer b) {
        static int asd = 15;
    }
}



