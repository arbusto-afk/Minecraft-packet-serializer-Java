package util;

import java.util.ArrayList;
import java.util.List;

public class ProtocolType {
    protected final List<Field> fields = new ArrayList<>();
    protected final String typeName;
    public ProtocolType(String typeName, Field fields) {
        this.fields.addAll(fields);
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "compType{" +
                "fields=" + fields +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
