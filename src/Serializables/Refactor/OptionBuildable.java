package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OptionBuildable implements Flattenable {
    @Override
    public String toString() {
        return "Option(" +
                flattenable +
                ')';
    }

    protected final Flattenable flattenable;
    public OptionBuildable(Flattenable flattenable){
        this.flattenable = flattenable;
    }

    @Override
    public Flattenable clone() {
        return new OptionBuildable(flattenable);
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        return flattenable.fsArr(name);
    }


    @Override
    public String stringify(String name) {
       return flattenable.stringify(name);
    }

    @Override
    public boolean equals(Object o) {

        // Check if the object is being compared to itself
        if (this == o) return true;

        // Check if the object is null or not an instance of ClassBuildable
        if (o == null || getClass() != o.getClass()) return false;

        // Cast the object to ClassBuildable
        OptionBuildable that = (OptionBuildable) o;

        // Compare the clazz fields
        return that.getFlattenable() != null ? getFlattenable() .equals(that.getFlattenable() ) : that.getFlattenable()  == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlattenable());
    }

    @Override
    public List<PacketField> asPacketFields() {
        return flattenable.asPacketFields();
    }

    public Flattenable getFlattenable() {
        return flattenable;
    }
}
