package Serializables.Refactor;

import Serializables.Consts;
import Serializables.NativeTypesEnum;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.Pair;
import Serializables.Types.*;

import java.sql.Ref;
import java.util.List;
import java.util.stream.Collectors;

public class BitfieldField extends ContainerField {
    int size;
    boolean signed;
   // String name;

    public BitfieldField(int size, boolean signed, String name) {
        super(null, name);
        this.size = size;
        this.signed = signed;
    }
    public BitfieldField clone(){
        return new BitfieldField(size, signed, name);
    }

    @Override
    public String toString() {
        return name + "(" + (signed ? "" : "u") + size + ")";
    }
    public static Class<?> getSizeClass(int bits, boolean signed) {
        if (bits <= 8) return signed ? u8.class : i8.class;
        if (bits <= 16) return signed ? u16.class : i16.class;
        if (bits <= 32) return signed ? u32.class : i32.class;
        if (bits <= 64) return signed ? u64.class : i64.class;
        throw new RuntimeException("Unsupported bits " + bits);
    }

    /*@Override
    public String[] flattenAsStringClassOnly() {
        return new String[]{getSizeClass(size).getSimpleName()};
    }

    @Override
    public String[] flattenAsString() {
        String str = Arrays.toString(flattenAsStringClassOnly());
        return new String[]{str.substring(1, str.length() - 1) + " " + name + ";\n"};
    }*/

    @Override
    public String stringify(String name) {
        return "//" + name +"_" + this.name+  " is a bitmask of size " + size + "\n\t" +getSizeClass(this.size, signed).getSimpleName() + " " + name + "_" + this.name + ";\n";
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        return new Pair[]{new Pair<>(this.name, getSizeClass(this.size, signed).getSimpleName())};
    }

    @Override
    public String[] getSerializers() {
        return new String[]{stringify("")};
    }
    @Override
    public List<PacketField> asPacketFields() {
      //  PacketField p = NativeTypesEnum.
        RefBuilder deserializerRef = new FuncRef("READBITFIELD", List.of(new ArgRef(Consts.BUFNAME.toString())));
        RefBuilder serializerRef = new FuncRef("WRITEBITFIELD", List.of(new ArgRef(Consts.BUFNAME.toString())));
        return List.of(new PacketField(name, "bitfield of size: " + size, BitfieldField.class, deserializerRef, serializerRef));
    }

    @Override
    public List<PacketField> asArrayFields() {
        return this.asPacketFields();
    }

    public int getSize() {
        return size;
    }
}
