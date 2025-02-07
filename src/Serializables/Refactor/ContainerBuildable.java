package Serializables.Refactor;

import Serializables.Refactor.Construct.ConstructItem;

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
    public Object getClasses()
    {
        return getContainerFields();
        /*
        List<Object> returnArr = new ArrayList<>();
        for(ContainerField cf : getContainerFields()){
            if(cf.getClasses().getClass().isArray()){
                returnArr.addAll(Arrays.asList((Object[])cf.getClasses()));
            } else if(cf.getClasses() instanceof Collection) {
                // Flatten nested collections
                returnArr.addAll((Collection<?>) cf.getClasses());
            } else {
                returnArr.add(cf.getClasses());
            }
        }
       return returnArr;
    */
    }

}
