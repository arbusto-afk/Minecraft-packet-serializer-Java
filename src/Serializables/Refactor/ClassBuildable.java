package Serializables.Refactor;

import Serializables.Refactor.Construct.ConstructItem;
import Serializables.Refactor.Construct.BasicConstruct;

import java.util.*;

public class ClassBuildable implements Buildable {
    private final Class<?> clazz;

    public ClassBuildable(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public ClassBuildable clone() {
        return new ClassBuildable(clazz);
    }

    @Override
    public ClassBuildable getBuildable() {
        return this;
    }

    @Override
    public String toString() {
        if(clazz == null) return "null";
        return extractLast(clazz.getSimpleName());
    }

    @Override
    public Object getClasses() {
        return clazz;
    }

    public Class<?> getBuildableClass() {
        return clazz;
    }
}
