package Serializables.Refactor;

import Serializables.Types.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ContainerBuildable implements Flattenable {
    protected final ContainerField[] buildables;
    public ContainerBuildable(ContainerField[] buildables) {
        this.buildables = buildables;
    }

    @Override
    public Flattenable clone() {
        return new ContainerBuildable(Arrays.copyOf(buildables, buildables.length));
    }

    @Override
    public String toString() {
        return "CB" +
                Arrays.toString(buildables);
    }

    public ContainerField[] getContainerFields() {
        return buildables;
    }

    @Override
    public Flattenable[] flatten()
    {
        return buildables;
    }

    @Override
    public String stringify(String name) {
        StringBuilder sb = new StringBuilder();
        for(Flattenable f : buildables){
            sb.append(f.stringify(name));
        }
        return sb.toString();
    }
    public String stringify() {
        StringBuilder sb = new StringBuilder();
        for(Flattenable f : buildables){
            if(f instanceof ContainerField cf) {
                sb.append(cf.stringify());
            } else {
                throw new RuntimeException("Attempting to stringify a non containerField without name");
            }
        }
        return sb.toString();
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        List<Pair<String, String>> p = new ArrayList<>();
        for(Flattenable f : buildables){
            p.addAll(Arrays.asList(f.fsArr("-")));
        }
        return p.toArray(new Pair[0]);
    }
    public String[] getSerializers() {
        List<String> p = new ArrayList<>();
        for(Flattenable f : buildables){
            p.addAll(Arrays.asList(f.getSerializers()));
        }
        return p.toArray(new String[0]);
    }

    @Override
    public List<PacketField> asPacketFields() {
        return Arrays.stream(buildables).map(ContainerField::asPacketFields).flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public List<PacketField> asArrayFields() {
        return Arrays.stream(buildables).map(ContainerField::asArrayFields).flatMap(List::stream).collect(Collectors.toList());

    }

    public ContainerField[] getBuildables() {
        return buildables;
    }

}
