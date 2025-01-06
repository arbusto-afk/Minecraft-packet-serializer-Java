package Serializables;

import Serializables.Complex.ComplexType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Packet {
    private final Integer MAXPACKETSIZE = 4096;
    List<Class<?>> fields;
    List<String> fieldsAsString;
    String name;

    public Packet(List<Class<?>> fields, String name) {
        this.fields = fields;
        this.name = name;
    }

    public Packet( String name, List<String> fieldsAsString) {
        this.fieldsAsString = fieldsAsString;
        this.name = name;
    }

    public ByteBuffer Serialize(List<Object> params) throws IllegalArgumentException{
        ByteBuffer buffer = ByteBuffer.allocate(MAXPACKETSIZE);
        if(fields.size() != params.size()) {
            throw new IllegalArgumentException("Expected " + fields.size() + " fields but got " + params.size());
        }
        for (int i = 0; i < fields.size(); i++) {
            Class<?> expectedType = fields.get(i);
            Object param = params.get(i);
            if(expectedType.isInstance(param)) {
               throw new IllegalArgumentException("Expected [" + fields+ "] but got [" + getClassesFromObjectList(params) + "]");
            }
            ComplexType paramType = (ComplexType) param;
            paramType.serializeInto(buffer);
        }
        return buffer;
    }
    private List<Class<?>> getClassesFromObjectList(List<Object> params) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for(Object param : params) {
            classes.add(param.getClass());
        }
        return classes;
    }


    @Override
    public String toString() {
        return name + "[" + fieldsAsString + "]";
    }
}
