package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ContainerField implements Flattenable {
    protected final Flattenable flattenable;
    protected final String name;
    protected boolean stackName = false;

    public String getName() {
        return name;
    }

    public ContainerField(Flattenable flattenable, String name) {


        if (flattenable instanceof ContainerField cf && !(flattenable instanceof BitfieldField)) {
            this.flattenable = cf.flattenable;
            this.name = name + "_" + cf.getName();
        } else {
            this.flattenable = flattenable;
            this.name = name;
        }
    }
    public ContainerField(Flattenable flattenable, String name, boolean stackName) {
        if (flattenable instanceof ContainerField cf && !(flattenable instanceof BitfieldField)) {
            this.flattenable = cf.flattenable;
            this.name = name + "_" + cf.getName();
        } else {
            this.flattenable = flattenable;
            this.name = name;
        }
        this.stackName = stackName;
    }

    @Override
    public Flattenable[] flatten() {
        if(flattenable == null)
            return new Flattenable[]{this};
        return new Flattenable[]{flattenable};
    }
    public ContainerField clone(){
        return new ContainerField(flattenable.clone(), name, stackName);
    }

    @Override
    public String toString() {
        return name + ": " + flattenable;
    }
    public String stringify() {
        if (flattenable instanceof ContainerField cf) {
            return cf.stringify(name);
        }
        return flattenable.stringify(name);
    }



    @Override
    public String[] getSerializers() {
        return flattenable.getSerializers();
    }
    /*
    @Override
    public String[] flattenAsString() {
        if(flattenable.length == 1) {
            if (flattenable[0] instanceof ClassBuildable) {
                return new String[]{(flattenable[0].flattenAsString())[0] + " " + name + ";\n"};
            } else if (flattenable[0] instanceof SwitchBuildable) {
                return flattenable[0].flattenAsString();
            } else if(flattenable[0] instanceof MapperBuildable mb){
                return Arrays.stream(flattenable[0].flattenAsString()).map(s -> s + " " + name + ";\n").toArray(String[]::new);
            }
            return new String[]{flattenable[0].flattenAsString()[0] + " " + name + ";\n"};
        } else {
            List<String> str = new ArrayList<>();
            for(Flattenable f :flattenable){
                str.addAll(Arrays.asList(f.flattenAsString()));
            }
            return str.toArray(new String[0]);
        }
   //     return Flattenable.super.flattenAsString();
    }

    public String[] flattenAsStringClassOnly(){
        if(flattenable.length == 1) {
            if (flattenable[0] instanceof ClassBuildable) {
                return new String[]{(flattenable[0].flattenAsString())[0]};
            }
            return new String[]{flattenable[0].flattenAsString()[0]};
        } else {
            List<String> str = new ArrayList<>();
            for(Flattenable f :flattenable){
                if (f instanceof ContainerField cf) {
                    str.addAll(Arrays.asList(cf.flattenAsStringClassOnly()));
                } else {
                    str.addAll(Arrays.asList(f.flattenAsString()));
                }
            }
            return str.toArray(new String[0]);
        }
      //  return Flattenable.super.flattenAsString();
    }
    */

    @Override
    public List<PacketField> asPacketFields() {

        var aux = flattenable.asPacketFields().stream().map(p -> p.prefixName(name)).toList();
        return aux;

    }

    @Override
    public List<PacketField> asArrayFields() {
        return flattenable.asArrayFields();
    }
}
