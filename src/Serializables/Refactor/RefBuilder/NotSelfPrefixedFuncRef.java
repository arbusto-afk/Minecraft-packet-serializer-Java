package Serializables.Refactor.RefBuilder;

import java.util.ArrayList;
import java.util.List;

public class NotSelfPrefixedFuncRef extends FuncRef{
    public NotSelfPrefixedFuncRef(String funcName, List<? extends RefBuilder> args) {
        super(funcName, args);
    }

    public NotSelfPrefixedFuncRef(String funcName, RefBuilder args) {
        super(funcName, args);
    }
    @Override
    public RefBuilder returnCopyAsPrefixInnerFuncsWithLambdas(String prefix) {
        List<RefBuilder> newArgs = new ArrayList<>();
        for(RefBuilder ref : args) {
            RefBuilder prefixedRef = ref.returnCopyAsPrefixInnerFuncsWithLambdas(prefix);
            newArgs.add(prefixedRef);
        }
        return new NotSelfPrefixedFuncRef(funcName, newArgs);
    }
}
