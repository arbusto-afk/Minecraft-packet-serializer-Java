package Serializables.Refactor.RefBuilder;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class FuncRef implements RefBuilder{

    protected String funcName;
    protected List<? extends RefBuilder> args;

    public FuncRef(String funcName, List<? extends RefBuilder> args) {
        this.funcName = funcName;
        this.args = args;
    }
    public FuncRef(String funcName, RefBuilder args) {
        this.funcName = funcName;
        this.args = List.of(args);
    }
    public String toString() {
        return funcName + "(" + String.join(", ", args.stream().map(RefBuilder::toString).toList()) + ")";
    }

    @Override
    public RefBuilder prefixName(String compareToName) {
        for(RefBuilder ref : args) {
            ref.prefixName(compareToName);
        }
        return this;
    }

    @Override
    public RefBuilder returnCopyAsPrefixInnerFuncsWithLambdas(String prefix) {
        List<RefBuilder> newArgs = new ArrayList<>();
        for(RefBuilder ref : args) {
            RefBuilder prefixedRef = ref.returnCopyAsPrefixInnerFuncsWithLambdas(prefix);
            newArgs.add(prefixedRef);
        }
        String lambdaPref = prefix + " ->";
        if(funcName.startsWith(lambdaPref) ) {
            return new FuncRef(funcName, newArgs);
        }
        return new FuncRef(lambdaPref + funcName, newArgs);
    }

    public List<? extends RefBuilder> getArgs() {
        return args;
    }
}
