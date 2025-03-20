package Serializables.Refactor.RefBuilder;

import java.util.List;

public interface RefBuilder {
    String toString();

    static TernaryRef basicTernaryRef(String compareToName, Condition value, RefBuilder rf){
        return new TernaryRef(new Condition("(" + compareToName + ".equals(" + value + "))"), rf, new ArgRef("null"));
    }
    private boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    static TernaryRef basicOrTernaryRef(String compareToName, List<String> values, RefBuilder rf){
        return new TernaryRef(values.stream().map(s -> new Condition("(" + compareToName + ".equals(" + (rf.isInteger(s) ? "": compareToName + "_map.get(") + ((rf.isInteger(s)) ? s :"\"" + s + "\")") + "))")).toList(), rf, new ArgRef("null"), "||");
    }
    static TernaryRef basicNandTernaryRef(String compareToName, List<String> values, RefBuilder rf){
        return new TernaryRef(values.stream().map(s -> new Condition("(!" + compareToName + ".equals(" + s + "))")).toList(), rf, new ArgRef("null"), "&&");
    }

    default RefBuilder prefixName(String compareToName){
        return this;
    }

    default RefBuilder returnCopyAsPrefixInnerFuncsWithLambdas(String lambdaName){ return this; }
}

