package Serializables.Complex;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class fArray extends Array{
    String countFieldName;
    public fArray(String countFieldName, TypeDescriptor type) {
        super((TypeDescriptor) null, type);
        this.countFieldName = countFieldName;
    }

    @Override
    public Object getBuilder() {
        List<Object> builder = new ArrayList<Object>();
        builder.add(Array.class);
        builder.add(countFieldName);
        builder.add(type.getBuilder());
        return builder;
    }
}
