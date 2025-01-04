package serializables.Complex;

import serializables.ComplexType;

import java.util.List;

public class SelfClassableType implements ComplexType {

    //must be complex to allow for position, and dynamics types, not other complexs like sitches or arrays
    Class<?> singleType;
    public SelfClassableType(Class<?> singleType) {
        this.singleType = singleType;
    }

    @Override
    public List<Class<?>> getBuilder() {
       //if(singleType instanceof ComplexType){
            return List.of(singleType);
       // }
      //  return new Class[]{singleType.getClass()};
    }
    @Override
    public String toString(){
        return singleType.getName();
    }


}
