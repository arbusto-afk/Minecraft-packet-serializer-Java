import util.ProtocolType;

import java.util.ArrayList;
import java.util.*;

public class Packet {
    final String name;
    //name, desc
  //  final List<ProtocolType> params = new ArrayList<>();
    final ProtocolType typeInstance;

    public Packet(String name, ProtocolType typeInstance){
        this.name = name;
        this.typeInstance = typeInstance;
    }
/*    public void addParam(ProtocolType type){
        params.add(type);
    }
  *//*  public void addParam(List<Field> type){
        params.addAll(type);
    }
*/
    @Override
    public String toString() {
        return name + ": " + typeInstance;

    }
}
