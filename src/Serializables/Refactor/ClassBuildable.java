package Serializables.Refactor;

public class ClassBuildable implements Buildable {
    private final Class<?> clazz;

    public ClassBuildable(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public ClassBuildable getBuildable() {
        return this;
    }
}
