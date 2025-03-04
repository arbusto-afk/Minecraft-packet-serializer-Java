package Serializables.Complex;

import Serializables.ProtocolType;

import java.nio.ByteBuffer;
import java.util.List;

public interface ComplexType extends ProtocolType {
    @Override
    default byte[] serialize(){
        throw new UnsupportedOperationException();
    }

    @Override
    default void serializeInto(ByteBuffer buffer){
        throw new UnsupportedOperationException();
    }

    Object getBuilder();
}
