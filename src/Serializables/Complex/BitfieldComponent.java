package Serializables.Complex;

import java.io.Serializable;

public class BitfieldComponent implements ComplexType {
    int size;
    boolean signed;
    String name;

    public BitfieldComponent(int size, boolean signed, String name) {
        this.size = size;
        this.signed = signed;
        this.name = name;
    }
    public Object getBuilder(){
        return name + "(" + +size +" bit)";
    }
}
