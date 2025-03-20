package Serializables.Refactor;

import Serializables.Consts;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.Refactor.RefBuilder.FuncRef;
import Serializables.Refactor.RefBuilder.RefBuilder;

import java.sql.Ref;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SSRB {
    private final Class<?> clazz;
    private final List<SSRB> generics;
    private String completeRef;
    private String simpleRef;
    private Integer arrayLevel = 0;




    public SSRB(Class<?> clazz, List<SSRB> generics) {
        this.clazz = clazz;
        this.generics = generics;
            this.completeRef = clazz.getSimpleName() + (generics.isEmpty() ? "" : "<" + String.join(", ", generics.stream().map(SSRB::getCompleteRef).toList()) + ">")
                    + ("[]".repeat(arrayLevel));
        this.simpleRef = clazz.getSimpleName() + ("[]".repeat(arrayLevel));
    }
    public SSRB(Class<?> clazz) {
        this( clazz, Collections.emptyList());
    }

    public Class<?> getClazz() {
        return clazz;
    }
    public List<SSRB> getGenerics() {
        return generics;
    }

    public String getCompleteRef(){
        return completeRef;
    }
    public String getSimpleRef(){
        return simpleRef;
    }
    public SSRB arraify(){
        System.out.println("Arrayfing to " + (arrayLevel + 1));
        this.arrayLevel++;
        this.completeRef = clazz.getSimpleName() + (generics.isEmpty() ? "" : "<" + String.join(", ", generics.stream().map(SSRB::getCompleteRef).toList()) + ">")
                + ("[]".repeat(arrayLevel));
        this.simpleRef = clazz.getSimpleName() + ("[]".repeat(arrayLevel));
        return this;
    }



    @Override
    public String toString() {
        return "SSRB{" +
                "clazz=" + clazz +
                ", generics=" + generics +
                ", completeRef='" + completeRef + '\'' +
                ", simpleRef='" + simpleRef + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o){
        // Check if the object is being compared to itself
        if (this == o) return true;

        // Check if the object is null or not an instance of ClassBuildable
        if (o == null || getClass() != o.getClass()) return false;

        // Cast the object to ClassBuildable
        SSRB that = (SSRB) o;


        if(this.clazz.equals(that.clazz)) {
            if (generics == null)
                throw new NullPointerException("generics is null");
            return generics.equals(that.generics);
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        // Use the hashCode of the clazz field
        return Objects.hash(clazz, generics, completeRef, simpleRef);
    }
}
