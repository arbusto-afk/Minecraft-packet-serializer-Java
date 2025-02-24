package Serializables;

import Serializables.Types.*;
import java.nio.ByteBuffer;


public class PacketBase {
    private final Object[] fields;
    private final Integer id;
    public PacketBase(Integer id, Object... fields){
        this.fields = fields;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private static final int TEMP_ALLOC_SIZE = 1024;
    public byte[] serialize(){
        ByteBuffer buffer = ByteBuffer.allocate(TEMP_ALLOC_SIZE);
        for(Object o : fields){
            switch(o){
                case i64 n: {

                    break;
                }
                case u64 as:{
                    break;

                }
                default: {

                }
            }
        }
   return null;
    }
}
