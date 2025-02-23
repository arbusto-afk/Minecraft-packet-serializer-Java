package Serializables.Refactor;

import Serializables.Types.Pair;

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
    public static Class<?> getSizeClass(int bits) {
        if (bits <= 8) return Byte.class;
        if (bits <= 16) return Short.class;
        if (bits <= 32) return Integer.class;
        if (bits <= 64) return Long.class;
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
        return "//" + name +"_" + this.name+  " is a bitmask of size " + size + "\n\t" +getSizeClass(this.size).getSimpleName() + " " + name + "_" + this.name + ";\n";
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        return new Pair[]{new Pair<>(this.name, getSizeClass(this.size).getSimpleName())};
    }
}
