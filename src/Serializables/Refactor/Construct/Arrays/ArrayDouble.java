package Serializables.Refactor.Construct.Arrays;

import Serializables.Refactor.ClassBuildable;

public class ArrayDouble<P, Q> extends AbsArray {
    ClassBuildable[] array;
    public ArrayDouble(P[] arr1, Q[] arr2) {
        super(new Class<?>[]{arr1.getClass(), arr2.getClass()}, arr1, arr2);
    }
}
