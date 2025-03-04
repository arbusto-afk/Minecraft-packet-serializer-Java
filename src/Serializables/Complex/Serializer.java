package Serializables.Complex;

import Serializables.JsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.ByteBuffer;

public class Serializer {
    private final String pcOrBedrock;
    private final String version;
    private JsonMapper protMapper;
    public Serializer(String pcOrBedrock, String version){
        this.version = version;
        this.pcOrBedrock = pcOrBedrock;
        ObjectMapper mapper = new ObjectMapper();
        try {
            protMapper = mapper.readValue(new File("minecraft-data/data/" + pcOrBedrock + "/" + version + "/protocol.json"), JsonMapper.class);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ByteBuffer serialize(String state, String clientboundOrServerBound, String packetName, Object[] params){
    //    PacketV2 packet = protMapper.getPacket(state, clientboundOrServerBound, packetName);
      //  Object builder = packet.getBuilder();
        return null;
    }
}
