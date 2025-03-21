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
        return new TernaryRef(values.stream().map(s -> {
            Boolean isIntegerOrBoolean =  s.matches("([0-9]{1,99})|true|false");
            String aux = isIntegerOrBoolean ? " == " : ".equals(";
            return new Condition("(" + compareToName + aux + (rf.isInteger(s) ? "": compareToName + "_map.get(") + (isIntegerOrBoolean ? s :"\"" + s + "\")") + (isIntegerOrBoolean ? "" : ")") + ")");
        }).toList(), rf, new ArgRef("null"), " || ");
    }

    static TernaryRef basicNandTernaryRef(String compareToName, List<String> values, RefBuilder rf){
        return new TernaryRef(values.stream().map(s ->
        {

                Boolean isIntegerOrBoolean =  s.matches("([0-9]{1,99})|true|false") ;
                String aux = isIntegerOrBoolean ? " != " : ".equals(";
                return new Condition((isIntegerOrBoolean ? "(": "(!") + compareToName + aux + (isIntegerOrBoolean ? "": compareToName + "_map.get(") + ((rf.isInteger(s)) ? s :"\"" + s + "\")") + (isIntegerOrBoolean ? "" : ")") + ")");

        //    new Condition("(!" + compareToName + ".equals(" + s + "))")
        }).toList(), rf, new ArgRef("null"), " && ");
    }

    default RefBuilder prefixName(String compareToName){
        return this;
    }

    default RefBuilder returnCopyAsPrefixInnerFuncsWithLambdas(String lambdaName){ return this; }
}

