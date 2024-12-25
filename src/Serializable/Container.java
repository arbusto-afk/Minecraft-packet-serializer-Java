package Serializable;

import util.Field;

import java.util.List;

public class Container {
    private final List<Field> fields;

    public Container(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }
}
