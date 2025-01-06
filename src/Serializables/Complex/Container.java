package Serializables.Complex;

import java.util.ArrayList;
import java.util.List;

public class Container implements ComplexType {

    private final List<ComplexType> contents;
    public Container(List<ComplexType> contents){
        this.contents = contents;
    }

    public List<TypeDescriptor> getBuilder(){
        List<TypeDescriptor> classes = new ArrayList<>();
        for(ComplexType ct : contents){
            classes.addAll(ct.getBuilder());
        }
        return classes;
    }
}
