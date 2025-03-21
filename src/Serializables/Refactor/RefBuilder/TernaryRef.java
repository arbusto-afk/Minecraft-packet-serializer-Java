package Serializables.Refactor.RefBuilder;

import Serializables.Consts;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class TernaryRef implements RefBuilder{
    private List<Condition> conditions;
    private RefBuilder left;
    private RefBuilder right;
    private String join;

    public TernaryRef(Condition condition, RefBuilder left, RefBuilder right) {
        this(List.of(condition), left, right, null);
    }

    public TernaryRef(List<Condition> conditions, RefBuilder left, RefBuilder right, String join) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("left or right is null");
        }
        this.left = left;
        this.right = right;
        this.conditions = conditions;
        this.join = join;
    }

    public TernaryRef(List<Condition> conditions, RefBuilder left, RefBuilder right, String join, boolean hasIgnoredFirst) {
        if(left == null || right == null) {
            throw new IllegalArgumentException("left or right is null");
        }
        this.conditions = conditions;
        this.left = left;
        this.right = right;
        this.join = join;
        this.hasIgnoredFirst = hasIgnoredFirst;
    }

    @Override
    public String toString(){
        String join = this.join == null ? "E-213-NOJOIN@TERNARYREF" : this.join;
        return String.join(join, conditions.stream().map(Condition::toString).toList()) + " ? " + left + " : " + right;
    }
    @Override
    public RefBuilder prefixName(String compareToName){
        return prefixName(compareToName, hasIgnoredFirst);
    }

    private boolean hasIgnoredFirst = false;
  //  @Override
    public RefBuilder prefixName(String prefix, boolean hasIgnoredFirst) {

        List<Condition> newConditions = new ArrayList<>();
        if(hasIgnoredFirst ) {
            for (Condition s : conditions) {
                Condition newCond;
                String start = "(";
                if (s.toString().startsWith("(!")) {
                    start = "(!";
                }
                newCond = new Condition(start + prefix + "_" + s.toString().substring(1).trim());
                newCond.prefixEquals(prefix);
                newConditions.add(newCond);
            }
            this.conditions = newConditions;
        } else {

        }
        this.hasIgnoredFirst = true;
        if(left instanceof TernaryRef trf) {
            trf.prefixName(prefix, this.hasIgnoredFirst);
        } else {
            left.prefixName(prefix);
        }
        if(right instanceof TernaryRef trf) {
            trf.prefixName(prefix, this.hasIgnoredFirst);
        } else {
            right.prefixName(prefix);
        }
        return this;
    }

    @Override
    public RefBuilder returnCopyAsPrefixInnerFuncsWithLambdas(String prefix) {
        left.returnCopyAsPrefixInnerFuncsWithLambdas(prefix);
        right.returnCopyAsPrefixInnerFuncsWithLambdas(prefix);
        return new TernaryRef(conditions, left, right, join);
    }

}
