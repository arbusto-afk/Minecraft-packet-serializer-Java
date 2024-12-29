package neoutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PacketField {
    private final String name;
    private final String type;
    private final List<PacketField> subFields;

    public PacketField(String type, String name, List<PacketField> subFields) {
        this.type = type;
        this.name = name;
        this.subFields = subFields;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<PacketField> getSubFields() {
        return subFields;
    }

    @Override
    public String toString() {
        return "<" + name + "(" + (type != null ? type : subFields) + ")>";
    }

    public static List<PacketField> extractFields(Object type) {
        List<PacketField> fields = new ArrayList<>();

        if (type instanceof List<?> list) {
            String fieldType = list.get(0).toString();
            Object fieldDefinition = list.size() > 1 ? list.get(1) : null;
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
                    fields.add(new PacketField(fieldType, null, null));
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
