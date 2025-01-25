package Serializables.Complex;

import Serializables.Types.PrimitiveMapper;

import java.util.*;

public class TypeDescriptor {
    private final Class<?> type;
    private final String name;
    private final Object[] args;

    public TypeDescriptor(Class<?> type, String name) {
        this.type = type;
        this.name = name;
        this.args = new Object[]{};
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
    public Object getBuilder() {
        //if(cl)
        try {
            ArrayList<Object> builder = new ArrayList<Object>();
            if(args.length > 0) {
                for (Object arg : args) {
                    builder.add(((TypeDescriptor) (arg)).getBuilder());
                }
                return builder.toArray();
            } else {
                return PrimitiveMapper.getClassOrException(name).getDeclaredConstructors()[0].getParameterTypes()[0];
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

