package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<PacketField> asPacketFields() {
        return flattenable.asPacketFields();
    }
}
