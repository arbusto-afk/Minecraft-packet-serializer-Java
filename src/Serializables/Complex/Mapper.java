package Serializables.Complex;

public class Mapper {
    TypeDescriptor type;

    public Mapper(TypeDescriptor type) {
        this.type = type;
    }
    public Object getBuilder(){
        return type.getBuilder();
    }
}
