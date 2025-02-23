package Serializables.Refactor;

import Serializables.Types.Pair;

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
        return new ContainerField(flattenable.clone(), name);
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
    public String stringify(String name) {
        if(flattenable instanceof ContainerField cf) {
            return cf.stringify((stackName ? name + "_" + this.name: this.name));
        }
        return flattenable.stringify( name + "_" + this.name);
        /*switch (this.flattenable) {
            case ClassBuildable cb: {
                return new String[]{cb.getBuildableClass().getSimpleName() + " " + this.name + ";\n"};
            }
            case MapperBuildable mb: {
                String[] arr = mb.stringfy();
                arr[1] += " " + this.name + ";\n";
                return  arr;
            }
            case SwitchBuildable sb:{
                return resolvedSwitchStr(sb);
            }
            case RestBufferBuildable rbb: {
                return new String[]{rbb.stringfy()[0] + " " + this.name + ";\n"};
            }
            case PrefArrayBuildable pb: {
                return new String[]{pb.stringfy()[0] + " " + name + ";\n"};
            }
            default: {
                return flattenable.stringfy();
            }
        }
        */
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {;
        return flattenable.fsArr(name);
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
}
