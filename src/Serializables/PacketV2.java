package Serializables;

import Serializables.Refactor.ContainerBuildable;
import Serializables.Refactor.ContainerField;
import Serializables.Refactor.Flattenable;
import Serializables.Types.Tuples.Tuples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketV2 {
    private final Flattenable fields;
    //name, class, serializer method string refence
    //private final Tuples.Tuple3<String, String, String>[] fieldsDesc;
    private final String name;


    public PacketV2(String name, Flattenable fields) {
        this.fields = fields;
        this.name = name;
        Tuples.Tuple4 asd = new Tuples.Tuple4(1,2,3,4);
        //String[] names = fieldNames().split(",");
     //   String[] classes = classNames().split(",");
    //    List<String> aux = new ArrayList<>(Arrays.asList(names));

            try {
         //       aux.addAll(Arrays.asList(fields.getSerializers()));
            } catch (Exception e) {
            //    aux.addAll(Arrays.asList(fields.classesAsCommaSeparatedString("").split(",")));
            }
/*
        String[] serializers = aux.toArray(new String[aux.size()]);

        List<Tuples.Tuple3<String, String, String>> arr = new ArrayList<>();
        for(int i = 0; i < names.length; i++) {
            try {
                arr.add(new Tuples.Tuple3<>(names[i], classes[i], serializers[i]));
            } catch (Exception e) {
                arr.add(new Tuples.Tuple3<>(names[i], classes[i], ""));
            }
        }
        fieldsDesc = arr.toArray(new Tuples.Tuple3[arr.size()]);*/
    }

    public Flattenable getFields() {
        return fields;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name + ": <" + fields + ">\n";
    }

    public String[] flattenAsString() {
        List<String> flattened = new ArrayList<>();
        var f = fields;
            if (f instanceof ContainerField cf) {
                flattened.addAll(Arrays.asList(cf.stringify()));
            } else if (f instanceof ContainerBuildable cb) {
                flattened.addAll(Arrays.asList(cb.stringify()));
            } else {
                flattened.addAll(Arrays.asList(f.stringify("P")));
            }

        return flattened.toArray(new String[0]);
    }
    public String fieldNames(){
        StringBuilder sb = new StringBuilder();
        List<String> sl = new ArrayList<>();
            sl.add(fields.fieldNamesAsCommaSeparatedString("P"));

        return String.join(", ", sl).replace(", ,", ",");
    }
    public String classNames(){
        StringBuilder sb = new StringBuilder();
        List<String> sl = new ArrayList<>();

            sl.add(fields.classesAsCommaSeparatedString("P"));

        return String.join(", ", sl.stream().map(s ->
        {
            if (s.contains("<")) {
                return s.substring(0, s.indexOf("<"));
            } else
                return s;
        }
        ).toList()).replace(", ,", ",");
    }

}
