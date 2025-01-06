package Serializables;

import Serializables.Complex.ComplexType;
import Serializables.Complex.TypeDescriptor;

import java.util.ArrayList;
import java.util.List;

public class PacketV2 {
    private final ComplexType fields;
    private final String name;
    public PacketV2(String name, ComplexType fields) {
        this.fields = fields;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<TypeDescriptor> getBuilder(){
        return fields.getBuilder();
    }

    @Override
    public String toString(){

        return name + ": <" + fields.getBuilder() + ">\n";
    }



}
