package Serializables.Refactor;

public class RestBufferBuildable implements Flattenable {

    public RestBufferBuildable clone() {
        return this;
    }

    @Override
    public String stringify(String name) {
        return "Byte[] " + name + ";\n";
    }
}
