package Serializables.Complex;

import Serializables.Types.PrimitiveMapper;

import java.util.ArrayList;
import java.util.List;

public class Option implements ComplexType{
    TypeDescriptor type;
    public Option(TypeDescriptor type){
        this.type = type;
    }
    public Object getBuilder(){
        List<Object> l = new ArrayList<Object>();
        l.add(PrimitiveMapper.getClassOrException("bool"));
        l.add(type.getBuilder());
        return l;
    }
}
