package Serializables.Refactor.RefBuilder;

public class MapEntryRef extends ArgRef{
    public MapEntryRef(String key, String value) {
        super("Map.entry("+  key + "," + value +")");
    }
}
