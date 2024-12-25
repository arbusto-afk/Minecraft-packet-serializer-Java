package util;

import java.util.ArrayList;
import java.util.List;

public class Bitfield extends ProtocolType {
    private final List<Integer> sizes = new ArrayList<>();
    public Bitfield(String bitfieldName, List<Field> fields, List<Integer> sizes){
        super(bitfieldName, fields);
        this.sizes.addAll(sizes);
    }

    @Override
    public String toString() {
        return "Bitfield{" +
                "fields=" + super.fields +
                "sizes=" + sizes +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
