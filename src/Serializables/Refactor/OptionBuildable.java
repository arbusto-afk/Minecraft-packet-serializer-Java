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

    protected final Flattenable[] flattenable;
    public OptionBuildable(Flattenable[] flattenable){
        this.flattenable = flattenable;
    }

    @Override
    public Flattenable clone() {
        return new OptionBuildable(flattenable);
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        List<Pair<String, String>> arr = new ArrayList<Pair<String, String>>();
        for(Flattenable f : flattenable){
            arr.addAll(Arrays.stream(f.fsArr(name)).map(
                    p -> new Pair<>("optional " + p.getRight() + " " + p.getLeft(), p.getRight())).toList()
            );
        }
        return arr.toArray(new Pair[arr.size()]);
    }


    @Override
    public String stringify(String name) {
        StringBuilder sb = new StringBuilder();
        boolean debugFlagCurrentlyInContainerFieldItAddsATabSoIRemoveOneHereToCompensate = false;
        for(Flattenable f : flattenable){
            if(debugFlagCurrentlyInContainerFieldItAddsATabSoIRemoveOneHereToCompensate) {
                sb.append("\t").append(f.stringify(name));
            } else {
                sb.append(f.stringify(name));
            }
            debugFlagCurrentlyInContainerFieldItAddsATabSoIRemoveOneHereToCompensate = true;
      }
        return sb.toString();
    }
}
