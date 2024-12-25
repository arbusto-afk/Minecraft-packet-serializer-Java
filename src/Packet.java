import util.Field;

import java.util.ArrayList;
import java.util.*;

public class Packet {
    final String name;
    //name, desc
    final List<Pair<Field, String>> params = new ArrayList<>();

    public Packet(String name){
        this.name = name;
    }
    public void addParam(Field type, String desc){
        params.add(new Pair<>(type, desc));
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for(Pair<Field, String> p : params) {
            strb.append("          -").append(p.getRight()).append("[").
                    append(p.getLeft().toString().
                            substring(p.getLeft().toString().
                                    lastIndexOf(".") + 1)).append("], " + "\n");
        }
        return name + ": \n" + strb;

    }
}
