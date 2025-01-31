package Serializables.Refactor;

import java.util.Map;

public class SwitchBuildable implements Buildable{
    protected final String compareToFieldName;
    protected final Map<String, Buildable> fields;
    protected final Buildable defaultField;

    public SwitchBuildable(String compareToFieldName, Map<String, Buildable> fields, Buildable defaultField) {
        this.compareToFieldName = compareToFieldName;
        this.fields = fields;
        this.defaultField = defaultField;
    }
    public SwitchBuildable(String compareToFieldName, Map<String, Buildable> fields) {
        this(compareToFieldName, fields, null);
    }
    public SwitchBuildable getBuildable() {
        return this;
    }

}
