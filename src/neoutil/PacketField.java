package neoutil;

public class PacketField {
    private String name;
    private String type;

    public PacketField(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "<" + name +
                "(" + type + ')' +
                '>';
    }
}
