package Serializables.Refactor;

import Serializables.Refactor.Construct.ConstructItem;

import java.util.LinkedHashSet;
import java.util.List;

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
    public Object getClasses() {
        return buildable.getClasses();
    }
    public ContainerField clone(){
        return new ContainerField(buildable.clone(), name);
    }

    @Override
    public String toString() {
        if(buildable.getClasses() instanceof Class<?> c)
            return name + "(" + c.getSimpleName() + ")";
        return name + ": " + buildable.getClasses();
    }
}
