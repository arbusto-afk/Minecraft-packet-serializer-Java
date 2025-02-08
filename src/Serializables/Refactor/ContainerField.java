package Serializables.Refactor;

public class ContainerField implements Buildable {
    protected final Buildable  buildable;
    protected final String name;

    public Buildable getBuildable() {
        return buildable;
    }

    public String getName() {
        return name;
    }

    public ContainerField(Buildable buildable, String name) {
        this.buildable = buildable;
        this.name = name;
    }

    @Override
    public Object flatten() {
        return buildable.flatten();
    }
    public ContainerField clone(){
        return new ContainerField(buildable.clone(), name);
    }

    @Override
    public String toString() {
        if(buildable.flatten() instanceof Class<?> c)
            return name + "(" + c.getSimpleName() + ")";
        return name + ": " + buildable.flatten();
    }
}
