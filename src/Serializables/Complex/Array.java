package Serializables.Complex;

import Serializables.ProtocolType;

import java.util.ArrayList;
import java.util.List;

public class Array implements ComplexType {

    TypeDescriptor type;
    TypeDescriptor countType;
    Integer fixedCount;
    public Array(TypeDescriptor countType, TypeDescriptor type) {
        this.type = type;
        this.countType = countType;
        fixedCount = 0;
    }
    public Array(Integer fixedCount, TypeDescriptor type) {
        this.type = null;
        this.countType = countType;
        this.fixedCount = fixedCount;
    }

    @Override
    public Object getBuilder() {

        //aca tendria que crear una clase que tome los parametros necesarios porque si me pasan un container se rompe
        //container a, b no puedo hacer arr of(a ,b) si fuese uno solo si podriam a pensar loco,
        //pero me estoy muriendo de sueñox|x
        //return ProtMapper2.type;
        //
        //
        List<Object> builder = new ArrayList<Object>();
        builder.add(Array.class);
        if(fixedCount != null) {
            builder.add(countType.getBuilder());
        } else {
            builder.add(fixedCount);
        }
        builder.add(type.getBuilder());
            return builder;
     //   return List.of(Object[].class);
    }
}
