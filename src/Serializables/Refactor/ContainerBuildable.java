package Serializables.Refactor;

public class ContainerBuildable implements Buildable{
    protected final Buildable[] buildables;
    public ContainerBuildable(Buildable[] buildables) {
        this.buildables = buildables;
    }

}
