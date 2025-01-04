package serializables.Complex;

import com.fasterxml.jackson.core.async.ByteBufferFeeder;
import serializables.ComplexType;
import serializables.ProtocolType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Container implements ComplexType {

    private final List<ComplexType> contents;
    public Container(List<ComplexType> contents){
        this.contents = contents;
    }

    public List<Class<?>> getBuilder(){
        List<Class<?>> classes = new ArrayList<>();
        for(ComplexType ct : contents){
            classes.addAll(ct.getBuilder());
        }
        return classes;
    }
}
