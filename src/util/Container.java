package util;

import java.util.ArrayList;
import java.util.List;

public class Container extends ProtocolType {
    protected final List<Field> fields = new ArrayList<>();
    protected final String typeName;
    public Container(String typeName, List<Field> fields) {
        this.fields.addAll(fields);
        this.typeName = typeName;
    }

    public boolean isSingleField(){
        return fields.size() == 1;
    }
    public Integer fieldsCount(){
        return fields.size();
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
