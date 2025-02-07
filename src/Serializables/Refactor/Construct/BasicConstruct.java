package Serializables.Refactor.Construct;

import java.util.Collections;
import java.util.Map;

public class BasicConstruct implements ConstructItem {
    Class<?> clazz;
    Map<String, String> possibleValues;
    public BasicConstruct(Class<?> clazz, Map<String, String> possibleValues) {
        this.clazz = clazz;
        this.possibleValues = possibleValues;
    }
    public BasicConstruct(Class<?> clazz) {
        this.clazz = clazz;
        possibleValues = Collections.emptyMap();
    }
    @Override
    public String toString() {
        if(possibleValues == Collections.EMPTY_MAP)
            return clazz.getSimpleName();
        return possibleValues.keySet().toString();
    }
}
