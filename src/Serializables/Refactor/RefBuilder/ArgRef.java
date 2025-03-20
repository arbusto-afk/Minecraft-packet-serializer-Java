package Serializables.Refactor.RefBuilder;

public class ArgRef implements RefBuilder {
    String name;
    public ArgRef(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
