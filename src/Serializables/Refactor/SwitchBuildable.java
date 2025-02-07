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

    @Override
    public String toString() {
        return "SwitchBuildable{" +
                "compareToFieldName='" + compareToFieldName + '\'' +
                ", fields=" + fields +
                ", defaultField=" + defaultField +
                '}';
    }

    public SwitchBuildable getBuildable() {
        return this;
    }

    public Buildable clone(){
        return new SwitchBuildable(compareToFieldName, fields, defaultField);
    }

}
