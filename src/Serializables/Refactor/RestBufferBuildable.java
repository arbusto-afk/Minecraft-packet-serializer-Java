package Serializables.Refactor;

import java.util.List;

public class RestBufferBuildable implements Flattenable {

    public RestBufferBuildable clone() {
        return this;
    }

    @Override
    public String stringify(String name) {
        return "Byte[] " + name + ";\n";
    }

    @Override
    public List<PacketField> asPacketFields() {
        return List.of();
    }
}
