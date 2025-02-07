package Serializables.Complex;

import Serializables.Refactor.BitfieldComponent;

import java.util.*;

public class TypeDescriptor {
    protected final Class<?> type;
    protected final String name;
    protected final Object[] args;
    protected String desc = "";

    public void SetDesc(String desc) {
        this.desc = desc;
    }

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
    public TypeDescriptor duplicate(){
        return new TypeDescriptor(type, name, args);
    }
    public Object getBuilder()  {
        //if(cl)
        try {
            if (args.length != 0) {
                if (type == Container.class) {
                    List<TypeDescriptor> arr = new ArrayList<TypeDescriptor>();

                    for (Object arg : args) {
                        if(arg instanceof TypeDescriptor) {
                            arr.add((TypeDescriptor) arg);
                        } else {
                            System.out.println("unrecognized class arg:" + arg + ":>" + arg.getClass());
                            arr.add(null);
                        }
                    }
                    TypeDescriptor[] arr2 = arr.toArray(new TypeDescriptor[arr.size()]);
                    var typeInstance = (ComplexType) type.getDeclaredConstructors()[0].newInstance((Object) (arr2));
                    return typeInstance.getBuilder();
                }
                else {
                    List<Class<?>> classes = new ArrayList<>();
                    for(Object arg : args) {
                        if (arg instanceof Map) {
                            classes.add(Map.class);
                        } else {
                            classes.add(arg.getClass());
                        }
                    }
                    Class<?>[] classArr = classes.toArray(new Class<?>[classes.size()]);
                    ComplexType typeInstance;
                    if(type == BitfieldComponent.class){
                        typeInstance = (ComplexType) type.getDeclaredConstructors()[0].newInstance(args);
                    }
                    else {
                         typeInstance = (ComplexType) type.getDeclaredConstructor(classArr).newInstance(args);
                    }
                    return typeInstance.getBuilder();
                }
                //return typeInstance.getBuilder();

            }
            else {
                return desc + "(" + extractLast(type.getName()) + ")";
/*        } catch(IllegalArgumentException ex) {
            System.out.println("argument mismatch for " + type + "with name: " + name);
                  for(Object arg : args) {
                      System.out.print(arg.getClass() + ", ");
                  }
            System.out.println(Arrays.toString(args));
            ex.printStackTrace();
            return args;
        } catch(NoSuchMethodException ex) {
            System.out.println("argument mismatch for " + type + "with name: " + name + "Coult not find constructor:");
            for(Object arg : args) {
                System.out.print(arg.getClass() + ", ");
            }
            System.out.println("All constructors:");
            System.out.println(Arrays.toString(type.getDeclaredConstructors()));
            System.out.println(Arrays.toString(args));
            ex.printStackTrace();
            return args;

            */     }
        }catch(RuntimeException e) {
            e.printStackTrace();
      //      System.out.println("Could not instantiate " + type + "(" + name + ")with args: " + Arrays.toString(args));
            throw e;
      //   return null;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //registryEntryHolderSet
    //IDset


}

