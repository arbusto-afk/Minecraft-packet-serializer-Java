package Serializables;

import Serializables.Complex.ComplexType;
import Serializables.Complex.TypeDescriptor;
import Serializables.Refactor.Buildable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class PacketV2 {
    private final Buildable fields;
    private final String name;
    public PacketV2(String name, Buildable fields) {
        this.fields = fields;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Buildable getBuildable(){
        return fields.getBuildable();
    }

    @Override
    public String toString(){
        return name + ": <" + fields.getBuildable() + ">\n";
    }



}
