package Serializables.Refactor;

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

  //  @Override
  //  public Object flatten() {
  //      return clazz;
   // }

    public Class<?> getBuildableClass() {
        return clazz;
    }
}
