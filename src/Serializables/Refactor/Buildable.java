package Serializables.Refactor;

public interface Buildable {
    default public Buildable getBuildable(){
        return this;
    };
}
