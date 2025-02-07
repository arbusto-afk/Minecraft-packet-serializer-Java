package Serializables.Refactor;

import Serializables.Refactor.Construct.ConstructItem;

import java.util.LinkedHashSet;
import java.util.List;

public class BitfieldComponent extends ContainerField {
    int size;
    boolean signed;
   // String name;

    public BitfieldComponent(int size, boolean signed, String name) {
        super(null, name);
        this.size = size;
        this.signed = signed;
    }
    public BitfieldComponent clone(){
        return new BitfieldComponent(size, signed, name);
    }

    @Override
    public String toString() {
        return "BFC{" + name + "(" + (signed ? "" : "u") + size + ")}";
    }

    @Override
    public Buildable getBuildable() {
        return this;
    }
    @Override
    public BitfieldComponent getClasses() {
        return this;
    }
}
