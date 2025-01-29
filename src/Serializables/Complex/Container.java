package Serializables.Complex;

import java.util.ArrayList;
import java.util.List;

public class Container implements ComplexType {

    private final List<TypeDescriptor> contents;
    public Container(TypeDescriptor[] contents){
        this.contents = List.of(contents);
    }

    public Object getBuilder(){
        List<Object> classes = new ArrayList<>();
        for(TypeDescriptor ct : contents){
            classes.add(ct.getBuilder());
        }
        return classes;
    }
}
