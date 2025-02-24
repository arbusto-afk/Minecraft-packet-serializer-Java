package Serializables.Types;

import Serializables.Complex.*;
import Serializables.ProtocolType;

public enum jsonDataNameToClassMapper {
    varint(VarInt.class),
    varlong(VarLong.class),
    optvarint(VarInt.class),
    //pstring,
    //buffer,
    bool(mcBool.class),
    u8(u8.class),
    u16(u16.class),
    u32(u32.class),
    u64(u64.class),
    i8(i8.class),
    i16(i16.class),
    i32(i32.class),
    i64(i64.class),
    f32(f32.class),
    f64(f32.class),
    UUID(Serializables.Types.UUID.class),
   // option(Option.class),
    pstring(mcString.class),
    anonymousNbt(Nbt.class),
    anonOptionalNbt(Nbt.class),
    optionalNbt(Nbt.class),
    //topBitSetTerminatedArray(TopBitSetTerminatedArray.class),
    nbt(Nbt.class);
//    bit(Bit.class);

    Class<? extends ProtocolType> serClass;
    jsonDataNameToClassMapper(Class<? extends ProtocolType> c){
        serClass = c;
    }
    Class<? > getClassOrException(){
        return this.serClass;
    }
    public static Class<?> getClassOrException(String s){
        Class<?> clazz = UnsupNativeUsedInPrimSC.class;
        try {
            clazz = valueOf(s).getClassOrException();
        } catch (Throwable ex){
            throw new RuntimeException();
//            return clazz;
        }
        return clazz;
    }

    public static jsonDataNameToClassMapper serialize(Class<?> c){
       // return valueOf();
        return null;
    }
}
