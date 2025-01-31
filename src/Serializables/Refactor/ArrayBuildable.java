package Serializables.Refactor;

public class ArrayBuildable implements Buildable {
    protected final Buildable countType;
    protected final Buildable type;
    public ArrayBuildable(Buildable countType, Buildable type) {
        this.countType = countType;
        this.type = type;
    }
    @Override
    public ArrayBuildable getBuildable() {
        return this;
    }
}
