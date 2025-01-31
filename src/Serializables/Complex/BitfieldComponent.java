package Serializables.Complex;

import Serializables.Refactor.Buildable;

import java.io.Serializable;

public class BitfieldComponent implements Buildable {
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
