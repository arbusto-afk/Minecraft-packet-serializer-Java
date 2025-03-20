package Serializables.Refactor;

import Serializables.Consts;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Types.Pair;
import Serializables.Types.Tuples.AbstractTuple;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrefArrayBuildable implements Flattenable {
    protected final ClassBuildable countType;
    protected final Flattenable type;
 //   protected final Flattenable[] builtType;
    public PrefArrayBuildable(ClassBuildable countType, Flattenable type) {
        this.countType = countType;
        this.type = type;
      //  builtType = type.flatten();
    }

    @Override
    public Flattenable clone() {
        return new PrefArrayBuildable(countType.clone(), type);
    }

    @Override
    public String toString() {
        return "ArrayBuildable{" +
                "countType=" + countType +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the object is compared with itself
        if (o == null || getClass() != o.getClass()) return false; // Check if the object is null or of a different class

        PrefArrayBuildable that = (PrefArrayBuildable) o; // Cast the object to PrefArrayBuildable

        // Check if countType and type arrays are equal
        if (!countType.equals(that.countType) || !type.equals(that.type)) return false;

        return true; // If all checks pass, the objects are equal
    }
    @Override
    public int hashCode() {
        // Use the hashCode of the clazz field
        return Objects.hash(countType, type);
    }
    int i = 0;
    @Override
    public List<PacketField> asPacketFields() {
        i++;
     //   System.out.println("Multiple calls to packetfield for same instance: " + i);
       // String finalDeserializerRef = Consts.PREFARRAYDESERIALIZER + "(" + Consts.BUFNAME +", " + getTypeDeserializerRef(auxDepth) +", "+ getSelfSimpleRef() + "::new)";
        var fields= type.asArrayFields();
        int size = fields.size();
        var firstField =fields.getFirst();
        Class<?> clazz = size == 1 ? firstField.getSsrb().getClazz(): AbstractTuple.getTupleClassForSize(size);
        RefBuilder firstArg = new ArgRef(Consts.BUFNAME.toString());
        List<RefBuilder> secondArgRefs = fields.stream().map(p -> p.getDeserializerMethod().returnCopyAsPrefixInnerFuncsWithLambdas("aux")).toList();
        RefBuilder secondArg;
        if(secondArgRefs.size() == 1) {
            secondArg = secondArgRefs.getFirst();
        } else {
            List<RefBuilder> secondArgsRefPrefixedWithBuf = new ArrayList<>(List.of(new ArgRef(Consts.BUFNAME.toString())));
            secondArgsRefPrefixedWithBuf.addAll(secondArgRefs);
            secondArg = new FuncRef(AbstractTuple.getTupleClassForSize(secondArgRefs.size()).getSimpleName() + Consts.TUPLEREADFROMMETHODNAME,
                    secondArgsRefPrefixedWithBuf).returnCopyAsPrefixInnerFuncsWithLambdas("aux");
        }
        Stream<RefBuilder> t1 = Stream.concat(Stream.of(firstArg), Stream.of(secondArg));
        Stream<RefBuilder> thirdArg = Stream.of(new ArgRef(clazz.getSimpleName() + "[]::new"));
        RefBuilder ref = new FuncRef(Consts.PREFARRAYDESERIALIZER.toString(),Stream.concat(t1, thirdArg).toList());

        if(size == 1){
          //  firstField;
            return List.of(new PacketField(firstField.getName(), firstField.getDesc(), firstField.getSsrb().arraify(), ref, new ArgRef("NOSERIALIZER@PREFARRAY")));
        } else {
         //   Class<AbstractTuple> tClass =
            return List.of(
                    new PacketField("", "", new SSRB(
                            clazz,
                            fields.stream().map(p -> p.getSsrb()).collect(Collectors.toList())
                    ).arraify(), ref ,new ArgRef("NOSERIALIZER@PREFARRAY")));
        }
    }
}
