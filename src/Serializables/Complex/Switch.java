package Serializables.Complex;

import java.util.*;

public class Switch implements ComplexType{
    Map<String,TypeDescriptor> fields;
    TypeDescriptor defaultType;
    String compareToFieldName;

    public Switch( String compareToFieldName, Map<String, TypeDescriptor> fields, TypeDescriptor defaultType) {
        this.fields = fields;
        this.defaultType = defaultType;
        this.compareToFieldName = compareToFieldName;
    }
    public Switch( String compareToFieldName, Map<String, TypeDescriptor> fields) {
        this.fields = fields;
        this.defaultType = null;
        this.compareToFieldName = compareToFieldName;
    }


   /* @Override
    public List<Object> getBuilder() {
        List<Object> builder = new ArrayList<>();

        for( Map.Entry<String,TypeDescriptor> entry : fields.entrySet() ) {
            builder.add(entry.getValue().getBuilder());
        }
        if(defaultType != null) {
            builder.add(defaultType.getBuilder());
        }
        return builder;
    }

    */
   public Map<String, Object> getBuilder() {
       Map<String, Object> map = new LinkedHashMap<>();
       map.put("FIELDNAME", compareToFieldName);
       if(defaultType != null) {
           map.put("default", defaultType.getBuilder());
       }
       map.putAll(fields);
       return map;
   }
}
