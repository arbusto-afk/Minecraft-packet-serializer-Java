package Serializables.Complex;

import java.util.Map;

public class Switch {
    Map<String,TypeDescriptor> fields;
    TypeDescriptor defaultType;
    String compareToFieldName;

    public Switch( String compareToFieldName, Map<String, TypeDescriptor> fields, TypeDescriptor defaultType) {
        this.fields = fields;
        this.defaultType = defaultType;
        this.compareToFieldName = compareToFieldName;
    }
}
