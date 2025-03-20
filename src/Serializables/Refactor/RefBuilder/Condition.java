package Serializables.Refactor.RefBuilder;

public class Condition {
    private String condition;
    public Condition(String condition) {
        if(condition == null || condition.isEmpty())
            throw new IllegalArgumentException("Condition cannot be null or empty");
        if(!condition.startsWith("(") || !condition.endsWith(")"))
            throw new IllegalArgumentException("Condition must be wrapped with '()'");
        this.condition = condition;
    }

    @Override
    public String toString() {
        return condition;
    }

    public void prefixEquals(String prefix) {
        String match = ".equals(";
        int eqlIndex = condition.lastIndexOf(match) + match.length();
        String s1 = condition.substring(0, eqlIndex);
        String s2 = condition.substring(eqlIndex);
        String finalCond =s1 +  prefix + "_" + s2;
        this.condition = finalCond;
    }
}
