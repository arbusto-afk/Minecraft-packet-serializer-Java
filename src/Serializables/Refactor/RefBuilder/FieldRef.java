package Serializables.Refactor.RefBuilder;

import java.sql.Ref;

public class FieldRef implements RefBuilder {

    private String name;

    public FieldRef(String name) {
        this.name = name;
    }

    @Override
    public RefBuilder prefixName(String prefix) {
        if(name.isEmpty()){
            this.name = prefix;
        } else {
            this.name = prefix + "_" + this.name;
        }
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
