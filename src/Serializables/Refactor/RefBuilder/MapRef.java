package Serializables.Refactor.RefBuilder;

import java.io.Serializable;
import java.sql.Ref;
import java.util.List;

public class MapRef implements RefBuilder{
    List<? extends RefBuilder> entries;

    public MapRef(List<MapEntryRef> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        RefBuilder ref = new FuncRef("Map.ofEntries", entries);
        return ref.toString();
    }
}
