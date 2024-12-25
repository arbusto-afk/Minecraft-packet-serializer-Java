import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.util.*;

import Serializable.Container;
import util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String version = "1.21.3";
        String pcOrBedrock = "pc";
        Handler tmp = new Handler(version, pcOrBedrock);
        /*List<Packet> arr = tmp.getAllPackets(version, pcOrBedrock);
    /*    for(Packet s : arr){
           if(s.toString().contains("*"))
                System.out.println("-" +s);
        }
        for(String t : tmp.cTypes.keySet()){
            System.out.println("Stablished compound type '" + t + "' with params: ");
        }*/
        TypeLoader tl = new TypeLoader(tmp.getProtocolRootNode());
        for(ProtocolType type : tl.types()){
            NativeTypes t;
            try{
                t = NativeTypes.valueOf(type.getTypeName());
            } catch (Exception ex){
                t = null;
            }
            System.out.println("Stablished " + (t != null ? "simple" : "compound") + " type: " + type);
        }
    }

}
class Handler {
    final String dataDir = "minecraft-data/data/";
    final String dataPathName = "dataPaths.json";
    final String dataPathsDir = dataDir + dataPathName;
    final File dataPathsFile;
    final ObjectMapper mapper = new ObjectMapper();
    final JsonNode protocolRootNode;

    public JsonNode getProtocolRootNode() {
        return protocolRootNode;
    }

    public final Map<String, Field> types = new HashMap<>();

    public final Map<String, ProtocolType> cTypes = new LinkedHashMap<>();


    public Handler(String version, String pcOrBedrock) throws IOException {
        dataPathsFile = new File(dataPathsDir);
        if (!dataPathsFile.exists())
            throw new IllegalArgumentException("dataPaths file not found at dir: " + dataPathsDir);
        protocolRootNode = getPropertyRootNode(version, pcOrBedrock, "protocol");
       // loadVersionTypes();
    }
    /*private void loadCompoundField(JsonNode cTypeNode, String nodeName){
        if(cTypeNode.isArray()){

            NativeTypes nType;
            switch(cTypeNode.get(0).asText()) {
                case "container": {
                    List<Field> fields = new ArrayList<>();
                    for(JsonNode n : cTypeNode.get(1)){
                       // String typeName = n.get("type").toString();
                        if(n.get("name") != null) {
                            Field field = new Field(Object.class, n.get("name").asText());
                            fields.add(field);
                        }
                    }
                    Container newc = new Container(fields);
                   // cTypes.put(nodeName, newc);
                }
                case "bitfield": {

                }
                case "array": {

                }
            }
           try {
                nType = NativeTypes.valueOf(cTypeNode.get(0).asText());
                for(JsonNode n : cTypeNode.get(1)){
                //    System.out.println("Attempting to load: " + n.toString());
                }
            } catch(Exception e) {
                nType = null;
            }
        } else {
            System.out.println("Unsup format for " + cTypeNode);
        }
    }
  *//*  private void loadVersionTypes() {
        for (NativeTypes n : NativeTypes.values()) {
            types.put(n.toString(), new Field(n.getLClass(), n.toString()));
        }
        JsonNode typesNode = this.protocolRootNode.get("types");
        for (Iterator<String> it = typesNode.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            if(typesNode.get(key).asText().equals("native")){
                if(!types.containsKey(key))
                    System.out.println("Found unsupported native type: " + key);
            } else {
              //  System.out.println("Found compound type: " + key + " with params: ");
                JsonNode compoundTypeNode = typesNode.get(key);
                /*for(JsonNode n : compoundTypeNode){
                    System.out.print("-" + n.toString() + "\n");
                }

             //   System.out.println("");
                loadCompoundField(compoundTypeNode, key);
            }

        }
    }
*/
    private JsonNode getPropertyRootNode (String version, String pcOrBedrock, String property) throws IOException {
            if (!pcOrBedrock.equals("pc") && !pcOrBedrock.equals("bedrock"))
                throw new IllegalArgumentException("pcOrBedrock must be 'pc' or 'bedrock' but its " + pcOrBedrock);

            JsonNode propertyNode = mapper.readTree(this.dataPathsFile).get(pcOrBedrock).get(version).get(property);
            if (propertyNode == null)
                throw new RuntimeException("property " + property + " on " + pcOrBedrock + " " + version + " version not found at path " + this.dataPathsFile);
            File propertyFile = new File(dataDir + propertyNode.asText() + "/" + property + ".json");
            return mapper.readTree(propertyFile);
        }

    private Packet createPacketFromJsonNode(String name, JsonNode packetNode){
        Packet p = new Packet(name);
        for(JsonNode paramNode : packetNode.get(1)) {
            String desc = paramNode.get("name") == null ? "No desc" : paramNode.get("name").asText();
            String tN = paramNode.get("type") == null ? "Unsupported type" :
                    Objects.equals(paramNode.get("type").asText(), "") ? paramNode.get("type").toString() : paramNode.get("type").asText();
            if(paramNode.get("type") != null){

            }
            //Class<?> typeClass;
            String typeClass;
            try{
             //   typeClass = NativeTypes.valueOf(tN).getLClass().toString();
               typeClass = "sad:(";
                //     System.out.println("Found class: " + typeClass.getName());
            } catch (Exception e){
//                System.out.println("Could not find class: " + tN);

                typeClass = "***" + tN;
            }
          //  p.addParam(new Field(Object.class, typeClass), desc);
        }
        return p;
    }


    private List<Packet> collectPackets(String version, String pcOrBedrock, String type) {
        List<Packet> packets = new ArrayList<>();
        for(JsonNode node : this.protocolRootNode){
            //toClient,toServer
            JsonNode typeNode = node.get(type);
            if(typeNode != null){
                typeNode = typeNode.get("types");

                    //iterate over types
                    Iterator<String> fieldNames = typeNode.fieldNames();
                    while (fieldNames.hasNext()) {
                        String fieldName = fieldNames.next();
                        JsonNode packetNode = typeNode.get(fieldName);
                        packets.add(createPacketFromJsonNode(fieldName, packetNode));
                        //skip packet mapping ping 0x01 .. etc
                        /*if(!Objects.equals(fieldName, "packet")){
                            Packet p = new Packet(fieldName);
                            for (Iterator<String> it = packetsNode.get(fieldName).fieldNames(); it.hasNext(); ) {
                                String field = it.next();
                                //packetsNode.get(field).asText()
                                p.addParam(field, "n");
                                packets.add(p);
                            }
                        }*/
                    }
            }
        }
        return packets;
    }

    public List<Packet> getToClientPackets(String version, String pcOrBedrock) throws IOException {
        return collectPackets(version, pcOrBedrock, "toClient");
    }

    public List<Packet> getToServerPackets(String version, String pcOrBedrock) throws IOException {
        return collectPackets(version, pcOrBedrock, "toServer");
    }

    public List<Packet> getAllPackets(String version, String pcOrBedrock) throws IOException {
        List<Packet> ts = getToServerPackets(version, pcOrBedrock);
        List<Packet> tc = getToClientPackets(version,pcOrBedrock);
        tc.addAll(ts);
        return tc;
    }
}
class TypeLoader {
    private final JsonNode protocolRootNode;
    private final Map<String, ProtocolType> types = new LinkedHashMap<>();

    public boolean isTypeNode(JsonNode node){
        return node.size() == 2 && node.get("type") != null && node.get("name") != null;
    }

    public ProtocolType createCompoundType(JsonNode cTypeNode, String name) {
        ProtocolType retConstruct;
        List<Field> fields = new ArrayList<>();
        if (!cTypeNode.isArray())
            throw new RuntimeException("Attempting to create compoundType from JsonNode that isnt array");
        switch (cTypeNode.get(0).asText()) {
            case "container": {
                for (JsonNode subNode : cTypeNode.get(1)) {
                    if (isTypeNode(subNode)) {
                        ProtocolType a = types.get(subNode.get("type").asText());
                        fields.add(new Field(a, subNode.get("name").asText()));
                    }
                }
                return new ProtocolType(name, fields);
            }
            case "bitfield": {
                List<Integer> sizes = new ArrayList<>();
                for(JsonNode subNode : cTypeNode.get(1)){
                    ProtocolType a = types.get("Bitfield");
                    //todo
                    fields.add(new Field(types.get("i32"), subNode.get("name").asText()));
                    sizes.add(subNode.get("size").asInt(-1));
                }
                return new Bitfield(name, fields, sizes);
            }
            case "pstring": {
                return new cString(name, List.of(new Field(types.get("pstring"), "str"), new Field(types.get("varint"), "length")));
            }
        }
        ProtocolType pt = new ProtocolType(name, fields);
        return pt;
    }


    public TypeLoader(JsonNode protocolRootNode) {
        this.protocolRootNode = protocolRootNode;
        for(NativeTypes n : NativeTypes.values()){
            String typeName = n.toString();
            Field onlyField = new Field(types.get(typeName), typeName);
            ProtocolType type = new ProtocolType(typeName, List.of(onlyField));
            types.put(typeName, type);
        }
        types.put("void", null);

        JsonNode allTypesRootNode = this.protocolRootNode.get("types");
        for (Iterator<String> it = allTypesRootNode.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            JsonNode typeNode = allTypesRootNode.get(key);
            if(!typeNode.isArray() && typeNode.asText().equals("native")){
                System.out.println((types.containsKey(key) ? "" : "un") + "supported native type: " + key);
            } else {
                if(!typeNode.isTextual()) {
                    types.put(key, createCompoundType(typeNode, key));
                }
            }

        }
    }
    public Iterable<ProtocolType> types(){
        return this.types.values();
    }
}

