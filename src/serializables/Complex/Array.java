package serializables.Complex;

import serializables.ComplexType;
import serializables.ProtMapper2;
import serializables.ProtocolType;

import java.util.List;

public class Array implements ComplexType {

    ComplexType type;
    Class<ProtocolType> countType;
    public Array(Class<ProtocolType> countType, ComplexType type) {
        this.type = type;
        this.countType = countType;
    }

    @Override
    public List<Class<?>> getBuilder() {

        //aca tendria que crear una clase que tome los parametros necesarios porque si me pasan un container se rompe
        //container a, b no puedo hacer arr of(a ,b) si fuese uno solo si podriam a pensar loco,
        //pero me estoy muriendo de sueñox|x
        return ProtMapper2.type;
        //return type.getBuilder();

     //   return List.of(Object[].class);
    }
}
