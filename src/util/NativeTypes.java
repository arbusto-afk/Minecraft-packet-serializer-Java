package util;

import Serializable.Container;
import Serializable.VarInt;
import Serializable.VarLong;

import java.math.BigInteger;

public enum NativeTypes {
    u32(Long.class),
    u16(Integer.class),
    u8(Short.class),
    i64(Long.class),
    i32(Integer.class),
    i8(Byte.class),
    i16(Short.class),
    f32(Float.class),
    f64(Double.class),
    varint(VarInt.class),
    varlong(VarLong.class),
    u64(BigInteger.class),

    bool(Boolean.class),
    UUID(BigInteger.class),
    pstring(String.class),
    buffer(Byte[].class),

    bitfield(Bitfield.class),


    bitflags(Byte.class),
    restBuffer(Byte[].class);
  //  container(Container.class);
    Class<?> clazz;
    NativeTypes(Class<?> clazz){
        this.clazz = clazz;
    }
}
