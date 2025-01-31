package Serializables.Refactor;

public class OptionBuildable implements Buildable{
    protected final Buildable buildable;
    public OptionBuildable(Buildable buildable){
        this.buildable = buildable;
    }
}
