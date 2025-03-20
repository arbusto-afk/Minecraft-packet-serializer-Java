package Serializables;

import Serializables.Refactor.ClassBuildable;
import Serializables.Refactor.PacketField;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.SSRB;
import Serializables.Types.*;
import Serializables.Types.Void;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;
import java.util.function.DoubleToLongFunction;

public enum NativeTypesEnum {
    VARINT("varint",new ClassBuildable(
            Integer.class, Consts.VARINTDESERIALIZER.getF(),
            Consts.VARINT.getSerializerRef()
    )),
    VARLONG("varlong", new ClassBuildable(

                Long.class,
            Consts.VARLONGDESERIALIZER.getF(),
            Consts.VARLONG.getSerializerRef())),
    OPTVARINT("optvarint", new ClassBuildable(

                    Integer.class,
                    Consts.BOOLDESERIALIZER.getF(),
                    Consts.VARINT.getSerializerRef()
            )),
    BOOL("bool", new ClassBuildable(
            
                    Boolean.class,
                    Consts.BOOLDESERIALIZER.getF(),
                    Consts.BOOL.getSerializerRef()
            )),
    U8("u8", new ClassBuildable(
            
                    Short.class,
                    Consts.U8DESERIALIZER.getF(),
                    Consts.U8.getSerializerRef()
            )),
    U16("u16", new ClassBuildable(
            
                    Integer.class,
                    Consts.U16DESERIALIZER.getF(),
                    Consts.U16.getSerializerRef()
            )),
    U32("u32", new ClassBuildable(
            
                    Long.class,
                    Consts.U32DESERIALIZER.getF(),
                    Consts.U32.getSerializerRef()
            )),
    U64("u64", new ClassBuildable(
            
                    BigInteger.class,
                    Consts.U64DESERIALIZER.getF(),
                    Consts.U64.getSerializerRef()
            )),
    I8("i8", new ClassBuildable(
            
                    Byte.class,
                    Consts.I8DESERIALIZER.getF(),
                    Consts.I8.getSerializerRef()
            )),
    I16("i16", new ClassBuildable(
            
                    Short.class,
                    Consts.I16DESERIALIZER.getF(),
                    Consts.I16.getSerializerRef()
            )),
    I32("i32", new ClassBuildable(
            
                    Integer.class,
                    Consts.I32DESERIALIZER.getF(),
                    Consts.I32.getSerializerRef()
            )),
    I64("i64", new ClassBuildable(
            
                    Long.class,
                    Consts.I64DESERIALIZER.getF(),
                    Consts.I64.getSerializerRef()
            )),
    F32("f32", new ClassBuildable(
            
                    Float.class,
                    Consts.F32DESERIALIZER.getF(),
                    Consts.F32.getSerializerRef()
            )),
    F64("f64", new ClassBuildable(
            
                    Double.class,
                    Consts.F64DESERIALIZER.getF(),
                    Consts.F64.getSerializerRef()
            )),
    UUID("UUID", new ClassBuildable(
            
                    BigInteger.class,
                    Consts.UUIDDESERIALIZER.getF(),
                    Consts.UUID.getSerializerRef()
            )),
   // PSTRING("pstring", String.class),
    ANONYMOUSNBT("anonymousNbt",new ClassBuildable(
           
                   String.class,
                 //  Consts.ANONYMOUSNBTDESERIALIZER.getF(),
                   Consts.STRINGDESERIALIZER.getF(),
                   Consts.ANONYMOUSNBT.getSerializerRef()
           )),
    ANONOPTIONALNBT("anonOptionalNbt", new ClassBuildable(
            
                    String.class,
                 //   Consts.ANONOPTIONALNBTDESERIALIZER.getF(),
                     Consts.STRINGDESERIALIZER.getF(),
                    Consts.ANONOPTIONALNBT.getSerializerRef()
            )),
    OPTIONALNBT("optionalNbt", new ClassBuildable(
            
                    String.class,
                    //Consts.OPTIONALNBTDESERIALIZER.getF(),
                    Consts.STRINGDESERIALIZER.getF(),
                    Consts.OPTIONALNBT.getSerializerRef()
            )),
    BIT("bit", new ClassBuildable(
            
                    Boolean.class,
                    Consts.BITDESERIALIZER.getF(),
                    Consts.BIT.getSerializerRef()
            )),
    NBT("nbt", new ClassBuildable(
            
                    String.class,
                    Consts.NBTDESERIALIZER.getF(),
                    Consts.NBT.getSerializerRef()
            )),
    STRING("string", new ClassBuildable(
            
                    String.class,
                    Consts.STRINGDESERIALIZER.getF(),
                    Consts.STRING.getSerializerRef()
            )),
    VOID("void", new ClassBuildable(
            
                    Void.class,
                    new FuncRef("ERROR -477", new ArgRef(Consts.BUFNAME.toString())),
                    new FuncRef(":(", new ArgRef(":(("))
            ));


//    bit(Bit.class);

    private final String name;
    private final ClassBuildable ref;
    NativeTypesEnum(String name, ClassBuildable cb){
        this.name = name;
        this.ref =  cb;
    }


   // public Class<?> getClazz(){
  //      return clazz;
  //  }
    public String getName() {
        return name;
    }

    public ClassBuildable getClassBuildable(){
        return ref;
    //    return desc.isEmpty() ? new ClassBuildable(clazz): new ClassBuildable(clazz, desc);
    }

}
