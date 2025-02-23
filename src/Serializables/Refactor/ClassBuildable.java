package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.Objects;

public class ClassBuildable implements Flattenable {
    private final Class<?> clazz;

    public ClassBuildable(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public ClassBuildable clone() {
        return new ClassBuildable(clazz);
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

    @Override
    public String stringify(String name) {
        return clazz.getSimpleName() + " " + name + ";\n";
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        return new Pair[]{new Pair<>(name, clazz.getSimpleName())};
    }

    @Override
    public boolean equals(Object o){
        // Check if the object is being compared to itself
        if (this == o) return true;

        // Check if the object is null or not an instance of ClassBuildable
        if (o == null || getClass() != o.getClass()) return false;

        // Cast the object to ClassBuildable
        ClassBuildable that = (ClassBuildable) o;

        // Compare the clazz fields
        return clazz != null ? clazz.equals(that.clazz) : that.clazz == null;
    }
    @Override
    public int hashCode() {
        // Use the hashCode of the clazz field
        return Objects.hash(clazz);
    }
}
