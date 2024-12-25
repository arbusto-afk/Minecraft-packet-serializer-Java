package Serializable;

public class VarInt {
    private Integer n;
    public VarInt(Integer n){
        this.n = n;
    }
    public String toString(){
        return n.toString();
    }
    public byte[] serialize(){
        //todo
        return null;
    }
}
