package Serializables.Refactor;

import Serializables.Types.Pair;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SwitchBuildable implements Flattenable {
    protected final String compareToFieldName;
    protected final Map<String, Flattenable[]> fields;
    protected final Flattenable[] defaultField;

    public SwitchBuildable(String compareToFieldName, Map<String, Flattenable[]> fields, Flattenable[] defaultField) {
        this.compareToFieldName = compareToFieldName;
        this.fields = fields;
        this.defaultField = defaultField;
    }
    public SwitchBuildable(String compareToFieldName, Map<String, Flattenable[]> fields) {
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

    public Flattenable clone(){
        return new SwitchBuildable(compareToFieldName, fields, defaultField);
    }

    @Override
    public String stringify(String name) {
        //     return this.getClass().getSimpleName() + " " + name + ";\n";
        StringBuilder strb = new StringBuilder();
        List<String> skippedValues = new ArrayList<>();


        Map<List<Flattenable>, List<String>> map = new LinkedHashMap<>();
        // Cache for List<Flattenable> keys to ensure the same instance is reused
        Map<Set<Flattenable>, List<Flattenable>> listCache = new HashMap<>();

        for (Map.Entry<String, Flattenable[]> e : fields.entrySet()) {
            // Convert the array to a List<Flattenable>
            List<Flattenable> keyList = Arrays.asList(e.getValue());
            // Use a Set<Flattenable> to check for content equality
            Set<Flattenable> keySet = new HashSet<>(keyList);
            // Check if an equivalent key already exists in the cache
            List<Flattenable> cachedKey = listCache.get(keySet);

            if (cachedKey == null) {
                // If not, add the new key to the cache
                listCache.put(keySet, keyList);
                cachedKey = keyList;
            }
            // Use the cached key in the main map
            map.computeIfAbsent(cachedKey, k -> new ArrayList<>()).add(e.getKey());
        }

        // Process the map entries
        for (Map.Entry<List<Flattenable>, List<String>> e : map.entrySet()) {
            if(e.getKey().size() == 1 && e.getKey().getFirst() instanceof ClassBuildable && ((ClassBuildable)e.getKey().getFirst()).fsArr("")[0].getRight().contains("Void")) {
                skippedValues.addAll(e.getValue());
            } else {
                StringBuilder s = new StringBuilder("\t//Only present if " + compareToFieldName + " is " + String.join(" or ", e.getValue()) + "\n");
                for (Flattenable k : e.getKey()) {
                    Object s2 = k.fsArr("")[0].getRight().replace("[]", "");
                    s.append("\t" + k.stringify("c" + String.join("or", e.getValue())+ "_" + name + "_" + s2));
                }
                strb.append(s);
            }
        }

        boolean skippedDefault = false;
        if(defaultField != null) {
            if(defaultField.length == 1 && defaultField[0] instanceof ClassBuildable && ((ClassBuildable)defaultField[0]).fsArr("")[0].getRight().contains("Void")) {
                skippedDefault = true;
            } else {
                strb.append("\t//Default field: \n");
                for (Flattenable subf : defaultField) {
                    strb.append("\t").append(subf.stringify(name));
                }
                strb.append("//Default field end\n");
            }

        }
        var strb2 = new StringBuilder();
        if(!skippedValues.isEmpty()) {
            strb2.append("//if ").append(compareToFieldName).append(" is any of ").append(Arrays.toString(skippedValues.toArray())).append(skippedDefault ? "or defaults " : "").append("all fields are empty\n");
        }
        return strb2.toString()  + strb.toString() + "\n";
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        return new Pair[]{new Pair<>("Switch: " + this.toString(), "Object")};
    }
}
