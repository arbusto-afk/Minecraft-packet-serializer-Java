package serializables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketV2 {
    private final ComplexType fields;
    private final String name;
    public PacketV2(String name, ComplexType fields) {
        this.fields = fields;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Class<?>> getBuilder(){
        return fields.getBuilder();
    }

    @Override
    public String toString(){
        final List<Class<? extends ProtocolType>> classes = new ArrayList<>();

        return name + ": " + fields.getBuilder() + "\n";
    }



}
