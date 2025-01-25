package Serializables;

import Serializables.Complex.ComplexType;
import Serializables.Complex.TypeDescriptor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PacketV2 {
    private final TypeDescriptor fields;
    private final String name;
    public PacketV2(String name, TypeDescriptor fields) {
        this.fields = fields;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object getBuilder(){
        return fields.getBuilder();
    }

    @Override
    public String toString(){

        return name + ": <" + fields.getBuilder() + ">\n";
    }



}
