package neoutil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import serializables.ProtocolType;
import serializables.Types.primSC;

import java.nio.ByteBuffer;
import java.util.*;

public class Packet {
    private final String name;
    private final List<PacketField> fields;
    private final List<Class<?>> fieldType = new ArrayList<>();

    private final List<ProtocolType> parameters = new ArrayList<>();

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonCreator
    public Packet(
            @JsonProperty("name") String name,
            @JsonProperty("type") Object type) {
        this.name = name;
        this.fields = extractFields(type);
      /*
        for(PacketField f : this.fields){
            Class<?> fieldClass = Object.class; // Default to Object.class
            try {
                String className = "serializables.Types.VarInt";
                fieldClass = Class.forName(className);
            } catch (Exception ex) {
                // Log or print the issue for debugging purposes
                System.err.println("Class not found: " + f.getType() + ", defaulting to Object.class");
            }
            fieldType.add(fieldClass);
        }
        */

    }

    public String getName() {
        return name;
    }

    public List<PacketField> getFields() {
        return fields;
    }
    public static String extractLast(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // Handle null or empty input
        }
        int lastDotIndex = input.lastIndexOf('.');
        return (lastDotIndex != -1) ? input.substring(lastDotIndex + 1) : input;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Packet{name='" + name + "', fields=[");
        /*for (PacketField field : fields) {
            sb.append("\n  ").append(field);
        }*/
        for(PacketField f : this.fields){
            Class<?> fieldClass = Object.class; // Default to Object.class
            try {
                //String className = "serializables.Types." + f.getType();
                fieldClass = primSC.getClazz(f.getType());
            } catch (Throwable ex) {
                // Log or print the issue for debugging purposes
                System.err.println("Class not found: " + f.getType() + ", defaulting to Object.class");
            }
            fieldType.add(fieldClass);
        }
        boolean flag = false;
        int i = 0;
        for(Class<?> c : fieldType){
            if(flag){
                sb.append(",");
            }
            flag = true;
            sb.append("").append(extractLast(c.getName()).equals("ProtocolType") ? ("*unsup") :  extractLast(c.getName()));
            i++;
        }
        sb.append("]}\n");
        return sb.toString();
    }


    public Class<?>[] getClasses(Map<String, List<PacketField>> typeMap) {
        List<Class<?>> resolvedClasses = new ArrayList<>();

        for (PacketField field : this.fields) {
            resolvedClasses.addAll(resolveFieldClasses(field, typeMap));
        }

        return resolvedClasses.toArray(new Class<?>[0]);
    }

    private List<Class<?>> resolveFieldClasses(PacketField field, Map<String, List<PacketField>> typeMap) {
        List<Class<?>> classes = new ArrayList<>();

        try {
            // Attempt to resolve as a primitive or known type
            Class<?> typeClass = primSC.getClazz(field.getType());
            if (typeClass == ProtocolType.class) {
                throw new RuntimeException(); // Trigger fallback for ProtocolType
            }
            classes.add(typeClass); // Add the resolved class for primitive/known type
            return classes;
        } catch (Throwable ex) {
            // Handle complex or custom types
         //   if(field.getType())

            //if simpleType
            Object typeDefinition = typeMap.get(field.getType());
            if (typeDefinition != null) {
                // Extract subfields for the complex type
                List<PacketField> subFields = extractFields(typeDefinition);

                // Recursively resolve each subfield's classes
                for (PacketField subField : subFields) {
                    classes.addAll(resolveFieldClasses(subField, typeMap));
                }
            } else {
                // Fallback to Object.class for unresolved types
                //alias or arr
                if(field.getType().contains("sf")){
                    String subString = field.getType().substring(4, field.getType().lastIndexOf(")"));
                    classes.add(primSC.getClazz(subString));
                }
                else if(field.getSubFields() != null && field.getSubFields().size() == 1){
                    String childType = field.getSubFields().getFirst().getType();
                    switch (childType){
                        case null: {
                        //    classes.add(primSC.getClazz((String)typeMap.get(field.getType())));
                            classes.add(null);
                        }
                        case "array":
                        default:
                            System.out.println("Could not find class: " + field.getType());
                            classes.add(Object.class);
                    }
                } else {
                    System.out.println("Could not find class: " + field.getType());
                    classes.add(Object.class);
                }
            }
        }

        return classes; // Return all resolved classes for the field
    }

    public static List<PacketField> extractFields(Object type) {
        List<PacketField> fields = new ArrayList<>();

        if (type instanceof List<?> list) {
            String fieldType = list.get(0).toString();
            Object fieldDefinition = list.size() > 1 ? list.get(1) : null;
          //  System.out.println(fieldType);
            switch (fieldType) {
                case "container" -> {
                    List<Map<String, Object>> subFieldList = (List<Map<String, Object>>) fieldDefinition;
                    for (Map<String, Object> subField : subFieldList) {
                        fields.add(new PacketField(
                                subField.get("type").toString(),
                                subField.get("name") == null ? "anon" : subField.get("name").toString(),
                                extractFields(subField.get("type"))
                        ));
                    }
                }
                case "array" -> {
                    Map<String, Object> arrayDetails = (Map<String, Object>) fieldDefinition;
                    PacketField countType = extractFields(arrayDetails.get("countType") == null ? arrayDetails.get("count") : arrayDetails.get("countType")).getFirst();
                    List<PacketField> arrayType = extractFields(arrayDetails.get("type"));
                    fields.add(new ArrayField("array", arrayType, countType));
                }
                case "option" -> {
                    fields.add(new OptionField(fieldType, "option", extractFields(fieldDefinition)));
                }
                default -> fields.add(new PacketField(fieldType, null, null));
            }
        } else if (type instanceof Map<?, ?> map) {
            String name = map.get("name") != null ? map.get("name").toString() : "anonymous";
            fields.add(new PacketField(
                    null,
                    name,
                    extractFields(map.get("type"))
            ));
        } else if (type instanceof String typeName) {
            fields.add(new PacketField(typeName, "sf", null));
        }

        return fields;
    }

}
