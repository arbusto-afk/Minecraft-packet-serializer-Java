package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProtocolType {
    protected final List<Field> fields = new ArrayList<>();
    protected final String typeName;
    public ProtocolType(String typeName, List<Field> fields) {
        this.fields.addAll(fields);
        this.typeName = typeName;
    }
    public String getTypeName() {
        return typeName;
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "compType{" +
                "typeName='" + typeName + '\'' +
                ",fields=" + fields +
                " '}";
    }

    public List<Field> asFields(){
        return fields;
    }

}
