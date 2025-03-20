package Serializables.Refactor;

import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.Pair;
import Serializables.Types.Void;

import java.sql.Ref;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SwitchBuildable implements Flattenable {
    protected final String compareToFieldName;
    protected final Map<String, Flattenable> fields;
    protected final Flattenable defaultField;

    public SwitchBuildable(String compareToFieldName, Map<String, Flattenable> fields, Flattenable defaultField) {
        this.compareToFieldName = compareToFieldName;
        this.fields = fields;
        this.defaultField = defaultField;
    }
    public SwitchBuildable(String compareToFieldName, Map<String, Flattenable> fields) {
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

    public Flattenable clone(){
        return new SwitchBuildable(compareToFieldName, fields, defaultField);
    }

    @Override
    public String[] getSerializers() {
        return new String[]{"switch.readFrom()"};
    }

    @Override
    public List<PacketField> asArrayFields() {
        RefBuilder deserializeRef = new FuncRef("readSwitchOf" + compareToFieldName, new ArgRef(""));
        RefBuilder serializeRef = new FuncRef("writeSwitchOf" + compareToFieldName, new ArgRef(""));
        return List.of(new PacketField("", "Switch" + compareToFieldName, Object.class, deserializeRef, serializeRef));

    }
    @Override
    public List<PacketField> asPacketFields() {
       // String name = oldname.equals("anon") ? "" : oldname;
        List<PacketField> finalFields = new ArrayList<>();

        List<String> possibleValues = new ArrayList<>();
       // final String ternaryStr =  compareToFieldName + ".equals(";

        Map<Flattenable, List<String>> cachedFlattenables = new LinkedHashMap<>();
        for(Map.Entry<String, Flattenable> e : fields.entrySet()) {
            cachedFlattenables.computeIfAbsent(e.getValue(), k -> new ArrayList<>()).add(e.getKey());
            possibleValues.add(e.getKey());
        }

        for(Map.Entry<Flattenable, List<String>> e : cachedFlattenables.entrySet()) {
       //     String condition = String.join(" || ", e.getValue().stream().map(s -> ternaryStr + s + ")").toList());
            int fieldsCount = e.getKey().asPacketFields().size();
            if(!(fieldsCount == 1 && e.getKey().asPacketFields().getFirst().getSsrb().getClazz().equals(Void.class))) {
                finalFields.addAll(e.getKey().asPacketFields().stream().map(
                        p -> new PacketField(p.getName(), p.getDesc(), p.getSsrb(),RefBuilder
                                .basicOrTernaryRef(
                                        compareToFieldName,
                                        e.getValue(),
                                        p.getDeserializerMethod()
                                ),
                                new ArgRef("SERIALIZERSWITCH"))

                ).toList());
            }
            //   possibleValues.add(condition);
        }
        if(defaultField != null) {
            if(!(defaultField.asPacketFields().size() == 1 && defaultField.asPacketFields().getFirst().getSsrb().getClazz().equals(Void.class))) {
                finalFields.addAll(defaultField.asPacketFields().stream().map(
                                p -> new PacketField(p.getName(), p.getDesc(), p.getSsrb(),RefBuilder
                                        .basicOrTernaryRef(
                                                compareToFieldName,
                                                possibleValues,
                                                p.getDeserializerMethod()
                                        ),
                                        new ArgRef("SERIALIZERSWITCH"))
                                ).toList());
            }
        }
        return finalFields;
    }

}
