package util;

public class Field {
    ProtocolType clazz;
    String className;
    public Field(ProtocolType clazz, String desc ){
        this.clazz = clazz;
        this.className = desc;
    }
    public String toString(){
        return className + "(" + clazz +")";
    }
}
