package Serializables;

import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.*;
import Serializables.Types.Void;

public enum Consts {
    BUFNAME("buf"),
    PROTOCOLDESERIALIZERS("ProtocolDeserializers"),
 //   STRINGDESERIALIZER(PROTOCOLDESERIALIZERS + ".readVarintPrefixedString"),
   // BOOLDESERIALIZER(PROTOCOLDESERIALIZERS + ".readProtocolBool"),
    PREFARRAYDESERIALIZER(PROTOCOLDESERIALIZERS + ".readVarintPrefixedArr"),
    FIELDARRAYDESERIALIZER(PROTOCOLDESERIALIZERS + ".readFixedArr"),
    ABSTRACTTUPLEREADFROM("AbstractTuple.readFrom"),
    TUPLEREADFROMMETHODNAME(".readFrom"),
    FIXEDARRAYDESERIALIZER(PROTOCOLDESERIALIZERS + ".readFixedArr"),

    VARINT("varint"),
    VARLONG("varlong"),
    OPTVARINT("optvarint"),
    BOOL("bool"),
    U8("u8"),
    U16("u16"),
    U32("u32"),
    U64("u64"),
    I8("i8"),
    I16("i16"),
    I32("i32"),
    I64("i64"),
    F32("f32"),
    F64("f64"),
    UUID("UUID"),
    ANONYMOUSNBT("anonymousNbt"),
    ANONOPTIONALNBT("anonOptionalNbt"),
    OPTIONALNBT("optionalNbt"),
    BIT("bit"),
    NBT("nbt"),
    STRING("string"),
    VOID("void"),

    VARINTDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readVarInt", new ArgRef(Consts.BUFNAME.toString()))),
    VARLONGDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readVarLong", new ArgRef(Consts.BUFNAME.toString()))),
    OPTVARINTDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readOptVarint", new ArgRef(Consts.BUFNAME.toString()))),
    BOOLDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readBool", new ArgRef(Consts.BUFNAME.toString()))),
    U8DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readU8", new ArgRef(Consts.BUFNAME.toString()))),
    U16DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readU16", new ArgRef(Consts.BUFNAME.toString()))),
    U32DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readU32", new ArgRef(Consts.BUFNAME.toString()))),
    U64DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readU64", new ArgRef(Consts.BUFNAME.toString()))),
    I8DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readI8", new ArgRef(Consts.BUFNAME.toString()))),
    I16DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readI16", new ArgRef(Consts.BUFNAME.toString()))),
    I32DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readI32", new ArgRef(Consts.BUFNAME.toString()))),
    I64DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readI64", new ArgRef(Consts.BUFNAME.toString()))),
    F32DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readF32", new ArgRef(Consts.BUFNAME.toString()))),
    F64DESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readF64", new ArgRef(Consts.BUFNAME.toString()))),
    UUIDDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readUUID", new ArgRef(Consts.BUFNAME.toString()))),
    NBTDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readNBT", new ArgRef(Consts.BUFNAME.toString()))),
    ANONYMOUSNBTDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".anonymousNbtDeserializer", new ArgRef(Consts.BUFNAME.toString()))),
    ANONOPTIONALNBTDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS+".anonOptionalNbtDeserializer", new ArgRef(Consts.BUFNAME.toString()))),
    OPTIONALNBTDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".optionalNbtDeserializer", new ArgRef(Consts.BUFNAME.toString()))),
    BITDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readBit", new ArgRef(Consts.BUFNAME.toString()))),
    STRINGDESERIALIZER(new FuncRef(PROTOCOLDESERIALIZERS + ".readString", new ArgRef(Consts.BUFNAME.toString()))),

   PEEKI8(new FuncRef(PROTOCOLDESERIALIZERS + ".peekI8", new ArgRef(Consts.BUFNAME.toString()))),
   PEEKI16(new FuncRef(PROTOCOLDESERIALIZERS + ".peekI16", new ArgRef(Consts.BUFNAME.toString()))),
   PEEKI32(new FuncRef(PROTOCOLDESERIALIZERS + ".peekI32", new ArgRef(Consts.BUFNAME.toString()))),
   PEEKI64(new FuncRef(PROTOCOLDESERIALIZERS + ".peekI64", new ArgRef(Consts.BUFNAME.toString()))),
   ;
    private final String s;
    private final FuncRef f;
    Consts(String s){
        this.s = s;
        f = null;
    }
    Consts(FuncRef f){
        this.f = f;
        this.s = f.toString();
    }
    public FuncRef getSerializerRef(){
    //    if(f == null){
      //      throw new RuntimeException("Attempting to acess serializer for a cont which has not deserializer");
     //   }
        return new FuncRef(s + ".serializeInto", new ArgRef(Consts.BUFNAME.toString()));
    }

    @Override
    public String toString() {
        return s;
    }

    public FuncRef getF() {

        return f;
    }

    /*
    Assumptions:
    switch i assume nested switchs compareTo are of same depth
    ~switchBuildable line ~150
     */
}
