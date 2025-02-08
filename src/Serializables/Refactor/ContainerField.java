package Serializables.Refactor;

import java.util.Arrays;

public class ContainerField implements Buildable {
    protected final Buildable[] buildable;
    protected final String name;

    public String getName() {
        return name;
    }

    public ContainerField(Buildable[] buildable, String name) {
        this.buildable = buildable;
        this.name = name;
    }

    @Override
    public Buildable[] flatten() {
        if(buildable == null)
            return new Buildable[]{this};
        return buildable;
    }
    public ContainerField clone(){
        return new ContainerField(buildable.clone(), name);
    }

    @Override
    public String toString() {
      //  if(buildable.flatten() instanceof Class<?> c)
     //       return name + "(" + c.getSimpleName() + ")";
        return name + ": " + Arrays.toString(buildable);
    }
}
