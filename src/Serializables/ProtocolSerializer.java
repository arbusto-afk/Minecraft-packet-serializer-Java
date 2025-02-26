package Serializables;

import Serializables.Refactor.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProtocolSerializer {
    private JsonMapper prot;

    public ProtocolSerializer(String version, String pcOrBedrock) {
        ObjectMapper mapper = new ObjectMapper();
        JsonMapper.setVersion(version);
        try {
            prot = mapper.readValue(new File("minecraft-data/data/" + pcOrBedrock + "/" + version + "/protocol.json"), JsonMapper.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        prot.generatePacketIdEnums(version);
        System.out.println(JsonMapper.unbuildableTypes);
        System.out.println(JsonMapper.unbuildableBehaviourals);
    }

    private PacketV2[] getPacket(String packetName){
        return (prot.getTotalPackets().get(packetName)).toArray(new PacketV2[0]);
    }
    private PacketV2 getPacket(String state, String toClientOrToServer, String packetName){
        if (prot.getPackets().get(state) == null) {
            throw new RuntimeException("state: " + state + " not found, only available:" + prot.getPackets().keySet());
        }
        if (prot.getPackets().get(state).get(toClientOrToServer) == null) {
            throw new RuntimeException(toClientOrToServer + " direction not found, only available:" + prot.getPackets().keySet());
        }
        if (prot.getPackets().get(state).get(toClientOrToServer).get(packetName) == null) {
            throw new RuntimeException(packetName + "not found at " + toClientOrToServer + " in " + state);
        }
        return prot.getPackets().get(state).get(toClientOrToServer).get(packetName);
    }
    public Object serialize(String state, String toClientOrToServer, String packetName, Object... args) {
        if (prot.getPackets().get(state) == null) {
            throw new RuntimeException("state: " + state + " not found, only available:" + prot.getPackets().keySet());
        }
        if (prot.getPackets().get(state).get(toClientOrToServer) == null) {
            throw new RuntimeException(toClientOrToServer + " direction not found, only available:" + prot.getPackets().keySet());
        }
        if (prot.getPackets().get(state).get(toClientOrToServer).get(packetName) == null) {
            throw new RuntimeException(packetName + "not found at " + toClientOrToServer + " in " + state);
        }
        PacketV2 packet = prot.getPackets().get(state).get(toClientOrToServer).get(packetName);
        Flattenable[] flattenable = packet.getFields();

        int argsIndex = 0;
        for(Flattenable field : flattenable) {
            System.out.println(argsIndex);
            switch (field){
                case ContainerField cf :
                   return null;
            //cf.
                case ClassBuildable cb: {
                    try {
                        Object type = cb.getBuildableClass().getConstructor(args[argsIndex].getClass()).newInstance(args[argsIndex]);
                    } catch(Exception ex){
                        throw new RuntimeException("Argument " + argsIndex + " of " + cb.getBuildableClass().getSimpleName() + " could not be constructed, expected type was " + field, ex);
                    }
                }
                default: {
                    System.out.println("Unhandled field: " + field.getClass() + "at Index " + argsIndex);
                    //throw new RuntimeException(":(");
                }
            }
            argsIndex++;
        }
        return null;
    }

    public String[] createPacketClass(String packetName){
        PacketV2 packet = getPacket(packetName)[0];
        String firstLine = "public class " + packetName + " {\n";
        String lastLine = "}\n";
        StringBuilder constructor = new StringBuilder();
        for(Flattenable cf : packet.getFields()){

        }
        return null;
    }
}
