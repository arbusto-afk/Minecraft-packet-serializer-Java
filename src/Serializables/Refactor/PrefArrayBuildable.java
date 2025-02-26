package Serializables.Refactor;

import Serializables.Types.Pair;
import Serializables.Types.jsonDataNameToClassMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PrefArrayBuildable implements Flattenable {
    protected final ClassBuildable countType;
    protected final Flattenable[] type;
 //   protected final Flattenable[] builtType;
    public PrefArrayBuildable(ClassBuildable countType, Flattenable[] type) {
        this.countType = countType;
        this.type = type;
      //  builtType = type.flatten();
    }

    @Override
    public Flattenable clone() {
        return new PrefArrayBuildable(countType.clone(), type);
    }

    @Override
    public String toString() {
        return "ArrayBuildable{" +
                "countType=" + countType +
                ", type=" + type +
                '}';
    }

    @Override
    public String stringify(String name) {

        if(type.length == 1 && type[0] instanceof ClassBuildable cb) {
            if (cb.getErrorDesc().isEmpty()) {
                if(type[0].fsArr("")[0].getRight().equals(jsonDataNameToClassMapper.bit.getClazz().getSimpleName()))
                {
                    return type[0].fsArr("")[0].getRight() + " "+ name + ";\n";
                }
                return type[0].fsArr("")[0].getRight() + "[] " + name + ";\n";
            } else {
                    if(type[0].fsArr("")[0].getRight().equals(jsonDataNameToClassMapper.bit.getClazz().getSimpleName()))
                {
                    return type[0].fsArr("")[0].getRight() + " " +name + ";\n";
                }
                return cb.getErrorDesc() + type[0].fsArr("")[0].getRight() + "[] " + name + ";\n";

            }
        }
        if(type.length == 1 && type[0] instanceof MapperBuildable){
            return type[0].fsArr("")[0].getRight() + "[] " + name + ";\n";
        }
        else if(type.length == 1 && type[0] instanceof PrefArrayBuildable ab){
            return ab.fsArr("")[0].getRight() + "[] " + name + ";\n";
        }
        else if(type.length == 1 && type[0] instanceof ContainerField cf){
            return cf.fsArr("")[0].getRight() + "[] " + name + ";\n";
        }
        String[] res = new String[2];
        res[0] = "";
        res[1] = "\tTupleX<";
        int i = 0;
        for(Flattenable t : type){
            String s = "-";
            if(t instanceof ContainerField cf)
                s = cf.getName();
            for(Pair<String, String> p1 : t.fsArr(s)){
                i++;
                res[0] += "//" + p1.getLeft() + "\n";
                res[1] += p1.getRight() + "[],";
            }
        }
        if (res[1].endsWith(",")) {
            res[1] = res[1].substring(0, res[1].length() - 1) + ">[]";
        }
        res[1] = res[1].replaceFirst("TupleX<", "Tuple" + i + "<");
        return res[0]  + res[1] + " " + name + ";\n";
    }

    @Override
    public Pair<String, String>[] fsArr(String name) {
        if(type.length == 1 && type[0] instanceof ClassBuildable || type[0] instanceof MapperBuildable){
            if(type[0].fsArr("")[0].getRight().equals(jsonDataNameToClassMapper.bit.getClazz().getSimpleName())){
                return new Pair[]{new Pair<>(name,type[0].fsArr("")[0].getRight())};
            }
            return new Pair[]{new Pair<>(name,type[0].fsArr("")[0].getRight() + "[]")};
        } else if(type.length == 1 && type[0] instanceof PrefArrayBuildable ab){
            return new Pair[]{new Pair<>(name,ab.fsArr("")[0].getRight() + "[]")};
        }
        String res = "TupleX<";
        int i = 0;
        for(Flattenable t : type){
            String s = "-";
            if(t instanceof ContainerField cf)
                s = cf.getName();
            for(Pair<String, String> p1 : t.fsArr(s)){
                i++;
                res += p1.getRight() + ",";
            }
        }
        if (res.endsWith(",")) {
            res = res.substring(0, res.length() - 1) + ">[]";
        }
        res = res.replaceFirst("TupleX<", "Tuple" + i + "<");
        return new Pair[]{new Pair<>(name, res)};
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if the object is compared with itself
        if (o == null || getClass() != o.getClass()) return false; // Check if the object is null or of a different class

        PrefArrayBuildable that = (PrefArrayBuildable) o; // Cast the object to PrefArrayBuildable

        // Check if countType and type arrays are equal
        if (!countType.equals(that.countType)) return false;
        if (type.length != that.type.length) return false;

        for (int i = 0; i < type.length; i++) {
            if (!type[i].equals(that.type[i])) return false;
        }

        return true; // If all checks pass, the objects are equal
    }
    @Override
    public int hashCode() {
        // Use the hashCode of the clazz field
        return Objects.hash(countType);
    }

}
