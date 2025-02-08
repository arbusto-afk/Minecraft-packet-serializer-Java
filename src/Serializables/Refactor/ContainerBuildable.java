package Serializables.Refactor;

import java.util.*;

public class ContainerBuildable implements Buildable{
    protected final ContainerField[] buildables;
    public ContainerBuildable(ContainerField[] buildables) {
        this.buildables = buildables;
    }

    @Override
    public Buildable clone() {
        return new ContainerBuildable(Arrays.copyOf(buildables, buildables.length));
    }

    @Override
    public String toString() {
        return "CB" +
                Arrays.toString(buildables);
    }

    public ContainerField[] getContainerFields() {
        return buildables;
    }

    @Override
    public Buildable[] flatten()
    {
        return buildables;
    }

}
