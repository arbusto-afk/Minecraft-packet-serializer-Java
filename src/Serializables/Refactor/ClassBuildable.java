package Serializables.Refactor;

import Serializables.Consts;
import Serializables.NativeTypesEnum;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.*;

import java.lang.Void;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ClassBuildable implements Flattenable {
    private final Class<?> clazz;
    private final String errorDesc;
    private final RefBuilder deserializerMethod;
    private final RefBuilder serializerMethod;

    public ClassBuildable(Class<?> clazz, RefBuilder deserializerMethod, RefBuilder serializerMethod) {
        this(clazz, "", deserializerMethod, serializerMethod);
    }
    public ClassBuildable(Class<?> clazz, String desc, RefBuilder deserializerMethod, RefBuilder serializerMethod) {
        this.clazz = clazz;
        errorDesc = desc;
        this.deserializerMethod = deserializerMethod;
        this.serializerMethod = serializerMethod;
    }

    @Override
    public ClassBuildable clone() {
        return new ClassBuildable(clazz, errorDesc, deserializerMethod, serializerMethod);
    }

    @Override
    public String toString() {
        if(clazz == null) return "null";
        return extractLast(clazz.getSimpleName());
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

    @Override
    public String[] getSerializers() {
        return new String[]{clazz == null ? "null" : clazz.getSimpleName() + "::" + "readFrom"};
    }

    @Override
    public List<PacketField> asPacketFields() {
        if(clazz == Void.class)
            return Collections.emptyList();

        return List.of(new PacketField("", errorDesc,clazz, deserializerMethod, serializerMethod));
    }
}
