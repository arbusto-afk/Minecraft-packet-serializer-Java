package Serializables.Refactor;

import java.lang.reflect.Array;
import java.util.*;

public class PrefArrayBuildable implements Buildable {
    protected final ClassBuildable countType;
    protected final Buildable type;
    protected final Object builtType;
    public PrefArrayBuildable(ClassBuildable countType, Buildable type) {
        this.countType = countType;
        this.type = type;
        builtType = flattenType();
    }
    @Override
    public PrefArrayBuildable getBuildable() {
        return this;
    }

    @Override
    public Buildable clone() {
        return new PrefArrayBuildable(countType.clone(), type);
    }

    @Override
    public String toString() {
        return "ArrayBuildable{" +
                "countType=" + countType +
                ", type=" + type +
                '}';
    }


    private Object[] flattenType() {
        List<Object> arrayDesc = new ArrayList<>();
        if(type.flatten() instanceof Class<?>) {
            arrayDesc.add(Array.newInstance((Class<?>) type.flatten(), 0).getClass());
         //   arrayDesc.add(countType.getClasses());
            return arrayDesc.toArray();
        }
        else if(type instanceof Buildable b) {
            if(b.flatten() instanceof Class<?>) {
                arrayDesc.add(Array.newInstance((Class<?>) b.flatten(), 0).getClass());
       //         arrayDesc.add(countType.getClasses());
                return arrayDesc.toArray();
            }
            if(b.flatten() instanceof List l) {
                arrayDesc.addAll(l);
            } else {
                arrayDesc.add(b.flatten());
            }
      //     arrayDesc.add(countType.getClasses());
            return  arrayDesc.toArray();
        }
        throw new RuntimeException("Unsupported type: " + type.getClass());
    }
}
