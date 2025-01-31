package Serializables.Refactor;

import java.util.Map;

public class MapperBuildable implements Buildable {
    protected final Map<String, String> possibleValues;
    protected final Buildable type;
    public MapperBuildable(Map<String, String> possibleValues, Buildable type) {
        this.possibleValues = possibleValues;
        this.type = type;
    }
}
