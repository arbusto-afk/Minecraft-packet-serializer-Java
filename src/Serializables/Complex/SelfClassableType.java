package Serializables.Complex;

import java.lang.reflect.Type;
import java.util.List;

public class SelfClassableType implements ComplexType {

    //must be complex to allow for position, and dynamics types, not other complexs like sitches or arrays
    Class<?> singleType;
    String name;
    public SelfClassableType(Class<?> singleType, String name) {
        this.name = name;
        this.singleType = singleType;
    }

    @Override
    public List<TypeDescriptor> getBuilder() {
       //if(singleType instanceof ComplexType){
            return List.of(new TypeDescriptor(singleType, name));
       // }
      //  return new Class[]{singleType.getClass()};
    }
    @Override
    public String toString(){
        return singleType.getName();
    }


}
