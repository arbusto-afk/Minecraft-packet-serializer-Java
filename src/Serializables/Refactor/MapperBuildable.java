package Serializables.Refactor;

import java.util.*;

public class MapperBuildable implements Buildable {
    @Override
    public String toString() {
        return "MapperBuildable{" +
                "possibleValues=" + possibleValues +
                ", type=" + type +
                '}';
    }

    protected final Map<String, String> possibleValues;
    protected final ClassBuildable type;
    public MapperBuildable(Map<String, String> possibleValues, ClassBuildable type) {
        this.possibleValues = possibleValues;
        this.type = type;
    }

    @Override
    public Buildable clone() {
        return new MapperBuildable(possibleValues, type);
    }

    @Override
    public Object flatten() {
        return possibleValues;
    }
}
