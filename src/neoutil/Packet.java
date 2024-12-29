package neoutil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import serializables.ProtocolType;
import serializables.Types.primSC;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Packet {
    private final String name;
    private final List<PacketField> fields;
    private final List<Class<?>> fieldType = new ArrayList<>();
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
        this.fields = PacketField.extractFields(type);
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


    public Class<?>[] getClasses(Map<String, Object> typeMap) {
        List<Class<?>> resolvedClasses = new ArrayList<>();

        for (PacketField field : this.fields) {
            resolvedClasses.addAll(resolveFieldClasses(field, typeMap));
        }

        return resolvedClasses.toArray(new Class<?>[0]);
    }

    private List<Class<?>> resolveFieldClasses(PacketField field, Map<String, Object> typeMap) {
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
            Object typeDefinition = typeMap.get(field.getType());
            if (typeDefinition != null) {
                // Extract subfields for the complex type
                List<PacketField> subFields = PacketField.extractFields(typeDefinition);

                // Recursively resolve each subfield's classes
                for (PacketField subField : subFields) {
                    classes.addAll(resolveFieldClasses(subField, typeMap));
                }
            } else {
                // Fallback to Object.class for unresolved types
                classes.add(Object.class);
            }
        }

        return classes; // Return all resolved classes for the field
    }

}
