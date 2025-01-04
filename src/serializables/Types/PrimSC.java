package serializables.Types;

import serializables.Complex.UnsupNativeUsedInPrimSC;

public enum PrimSC {
    varint(VarInt.class),
    varlong(VarLong.class),
    optvarint(Option.class),
    //pstring,
    //buffer,
    bool(Boolean.class),
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
    UUID(serializables.Types.UUID.class),
    option(Option.class),
    string(String.class);

    Class<?> serClass;
    PrimSC(Class<?> c){
        serClass = c;
    }
    Class<? > getClazz(){
        return this.serClass;
    }
    public static Class<?> getClazz(String s){
        Class<?> clazz = UnsupNativeUsedInPrimSC.class;
        try {
            clazz = valueOf(s).getClazz();
        } catch (Throwable ex){
            return clazz;
        }
        return clazz;
    }
}
