package Serializables.Refactor;

public class OptionBuildable implements Buildable{
    @Override
    public String toString() {
        return "Option(" +
                buildable +
                ')';
    }

    protected final Buildable buildable;
    public OptionBuildable(Buildable buildable){
        this.buildable = buildable;
    }

    @Override
    public Buildable clone() {
        return new OptionBuildable(buildable);
    }

}
