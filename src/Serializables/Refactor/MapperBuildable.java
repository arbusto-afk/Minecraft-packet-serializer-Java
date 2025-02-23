package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.*;

public class MapperBuildable implements Flattenable {
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
    public Flattenable clone() {
        return new MapperBuildable(possibleValues, type);
    }

//    @Override
//    public Object flatten() {
//        return possibleValues;
//    }

    @Override
    public String stringify(String name) {
        StringBuilder strb = new StringBuilder("//possible values: ");
        for(Map.Entry<String, String> entry : possibleValues.entrySet()) {
            strb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }
        strb.append("\n").append(type.stringify(name));
        return strb.toString();
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        StringBuilder strb = new StringBuilder(type.fsArr("-")[0].getRight() + " mapper: ");
        for(Map.Entry<String, String> entry : possibleValues.entrySet()) {
            strb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }
        return new Pair[]{new Pair<>(strb.toString(), type.fsArr("-")[0].getRight())};
    }
}
