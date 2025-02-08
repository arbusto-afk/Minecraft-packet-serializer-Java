package Serializables.Refactor;

import java.util.Map;
import java.util.stream.Collectors;

public class SwitchBuildable implements Buildable{
    protected final String compareToFieldName;
    protected final Map<String, Object> fields;
    protected final Buildable[] defaultField;

    public SwitchBuildable(String compareToFieldName, Map<String, Object> fields, Buildable[] defaultField) {
        this.compareToFieldName = compareToFieldName;
        this.fields = fields.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {
            if(e.getValue() instanceof ContainerBuildable) {
                return ((ContainerBuildable)e.getValue()).flatten();
            }
            return e.getValue();
        }));
        this.defaultField = defaultField;
    }
    public SwitchBuildable(String compareToFieldName, Map<String, Object> fields) {
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
