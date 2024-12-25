package Serializable;

public class VarLong{
    private final Long value;
    public VarLong(Long n){
        this.value = n;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VarLong{" +
                "n=" + value +
                '}';
    }
}
