package Serializables;

import Serializables.Refactor.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProtocolMapper {
    private ProtMapper2 prot;

    public ProtocolMapper(String version, String pcOrBedrock) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            prot = mapper.readValue(new File("minecraft-data/data/" + pcOrBedrock + "/" + version + "/protocol.json"), ProtMapper2.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getPacketConstructor(String state, String toClientOrToServer, String packetName) {
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
        Buildable buildable = packet.getBuildable();
        List<Object> args = new ArrayList<>();
        if (buildable instanceof ContainerBuildable cb) {
            for (ContainerField field : cb.getContainerFields()) {
                args.add(field.getBuildable().getClasses());
            }
            return args;
        } else if(buildable instanceof MapperBuildable mb){
            return mb.getClasses();
        }
        return null;
    }

}
