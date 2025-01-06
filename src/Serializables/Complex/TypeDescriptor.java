package Serializables.Complex;

import java.util.Arrays;

public class TypeDescriptor {
    private final Class<?> type;
    private final String name;
    private final Object[] args;

    public TypeDescriptor(Class<?> type, String name) {
        this.type = type;
        this.name = name;
        this.args = new Object[0];
    }
    public TypeDescriptor(Class<?> type, String name, Object[] args)
    {
        this.type = type;
        this.name = name;
        this.args = args;
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String extractLast(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // Handle null or empty input
        }
        int lastDotIndex = input.lastIndexOf('.');
        return (lastDotIndex != -1) ? input.substring(lastDotIndex + 1) : input;
    }

    @Override
    public String toString() {
       return name + " [" + extractLast(type.getName()) + "<" + Arrays.toString(args) + ">]";
    }
}

