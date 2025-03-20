package Serializables.Refactor;

import Serializables.PacketBase;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.MapEntryRef;
import Serializables.Refactor.RefBuilder.MapRef;
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

    public Map<String, String> getPossibleValues() {
        return possibleValues;
    }


    @Override
    public List<PacketField> asPacketFields() {
        List<MapEntryRef> entries = new ArrayList<>(possibleValues.size());
        for(Map.Entry<String, String> entry : possibleValues.entrySet()) {
            entries.add(new MapEntryRef("\"" + entry.getValue() + "\"", entry.getKey()));
        }
        var MapSsrb = new SSRB(Map.class, List.of(new SSRB(String.class), new SSRB(Integer.class)));
        PacketField p = new PacketField("map", "", MapSsrb, new MapRef(entries), new ArgRef("/*internal map only*/ null"));
        List<PacketField> ret = new ArrayList<>(type.asPacketFields());
        ret.add(p);
        return ret;
    }

    @Override
    public List<PacketField> asArrayFields() {
        return type.asArrayFields();
    }
}
