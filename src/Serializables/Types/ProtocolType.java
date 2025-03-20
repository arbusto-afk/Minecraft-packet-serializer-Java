package Serializables.Types;
import java.nio.ByteBuffer;

public interface ProtocolType {
        default byte[] serialize(){
                throw new RuntimeException("Method not implemented for : " + this.getClass().getName());
        }

        default void serializeInto(ByteBuffer buffer){
                throw new RuntimeException("Method not implemented for : " + this.getClass().getName());
        };
        //static void readFrom(ByteBuffer buffer);
}
