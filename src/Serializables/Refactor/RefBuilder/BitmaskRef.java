package Serializables.Refactor.RefBuilder;

import java.util.List;

public class BitmaskRef implements RefBuilder {
    String prefix;
    RefBuilder ref;
    ArgRef mask;

    public BitmaskRef(RefBuilder ref, ArgRef mask) {
        this.ref = ref;
        this.mask = mask;
    }
    public BitmaskRef(RefBuilder ref, ArgRef mask, String prefix) {
        this.ref = ref;
        this.mask = mask;
        this.prefix = prefix;
    }

    @Override
    public RefBuilder prefixName(String compareToName) {
        return ref.prefixName(compareToName);
    }

    @Override
    public RefBuilder returnCopyAsPrefixInnerFuncsWithLambdas(String lambdaName) {
        return new BitmaskRef(ref, mask, lambdaName + " ->");
    }

    @Override
    public String toString() {
        return  (prefix == null ? "": prefix) + "(int)(" + ref + " & " + mask + ")";
    }
}
