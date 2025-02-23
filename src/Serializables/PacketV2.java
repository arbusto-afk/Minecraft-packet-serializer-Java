package Serializables;

import Serializables.Refactor.ContainerBuildable;
import Serializables.Refactor.ContainerField;
import Serializables.Refactor.Flattenable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketV2 {
    private final Flattenable[] fields;
    private final String name;
    public PacketV2(String name, Flattenable[] fields) {
        this.fields = fields;
        this.name = name;
    }

    public Flattenable[] getFields() {
        return fields;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name + ": <" + Arrays.toString(fields) + ">\n";
    }

    public String[] flattenAsString() {
        List<String> flattened = new ArrayList<>();
        for(Flattenable f : this.fields) {
            if (f instanceof ContainerField cf) {
                flattened.addAll(Arrays.asList(cf.stringify()));
            } else if (f instanceof ContainerBuildable cb) {
                flattened.addAll(Arrays.asList(cb.stringify()));
            } else {
                flattened.addAll(Arrays.asList(f.stringify("P")));
            }
        }
        return flattened.toArray(new String[0]);
    }
    public String fieldNames(){
        StringBuilder sb = new StringBuilder();
        List<String> sl = new ArrayList<>();
        for(Flattenable f : this.fields) {
            sl.add(f.fieldNamesAsCommaSeparatedString("P"));
        }
        return String.join(", ", sl);
    }

}
