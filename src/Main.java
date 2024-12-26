import java.io.File;
import java.io.IOException;
import java.util.*;

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
        PacketLoader pl = new PacketLoader(tl.typesMap(), tmp.getProtocolRootNode());
        for(Packet p : pl.getAllPackets(version, pcOrBedrock)){
            System.out.println(p);
        }
    }

}
class PacketLoader{
    private final Map<String, ProtocolType> typeMap;
    private final JsonNode protocolRootNode;
    public PacketLoader(Map<String, ProtocolType> typeMap, JsonNode rootNode){
        this.typeMap = typeMap;
        this.protocolRootNode = rootNode;
    }
    private ProtocolType protocolTypeFromTextualNode(JsonNode node, String name){
        return null;
    }

    private ProtocolType createTypeInstanceFromNOde(JsonNode packetNode) {
     //   Packet p = new Packet(name);
        //todo
        /*
        switch(packetNode.get(0).asText()){
            case "container": {
                for (JsonNode node : packetNode.get(1)) {
                    //if (!node.isTextual()) {
                        if(node.get("type").isTextual()) {
                            ProtocolType type = typeMap.get(node.get("type").asText());
                            String desc = node.get("name") != null ? node.get("name").asText() : "NoName";
                          //  p.addParam(new Field(type, desc));
                //        }
                    }
                }
            }
            case "bitfield": {
             //   List<F>
                for (JsonNode node : packetNode.get(1)){
                }
            }
        }*/

        if(packetNode.isArray()){

            List<Field> fields = new ArrayList<>();
            /* todo
            todo curently skipping first element of an array
            todo since i assume it will always be a descrtipr
            todo descriptor= ("switch", "container", etc)

             */
            if(!packetNode.isEmpty() && packetNode.get(0).asText().equals("switch")){
                return new ProtocolType("unsupSwitch", fields);
            }
            boolean flag = false;
            for(JsonNode subNode : packetNode){
                if(flag) {
                    ProtocolType subType = createTypeInstanceFromNOde(subNode);
                    fields.addAll(subType.getFields());
                } else {
                    flag = true;
                }
            }
            return new ProtocolType("777", fields);
        } else if(packetNode.isObject()) {
            String name = "noname, maybe typeName?";
            if(packetNode.get("name") != null) {
                name = packetNode.get("name").asText();
            }
            JsonNode typeNode;
            if(packetNode.get("type") != null) {
             typeNode = packetNode.get("type");
            } else if(packetNode.get("size") != null) {
                typeNode = packetNode.get("size");
            }else {
                typeNode = packetNode.get("countType");
            }
            if(typeNode == null)
                typeNode = packetNode.get("count");
            if (typeNode.isTextual() || typeNode.isInt()) {
                String typeName = typeNode.asText();
                Field onlyField = new Field(typeMap.get(typeName), name);
                return new ProtocolType(name, List.of(onlyField));
            } else if (typeNode.isArray() || typeNode.isObject()) {
                return createTypeInstanceFromNOde(typeNode);
            }
            throw new RuntimeException("Obj node, Type is nor arr nor text :(");
        }
        else if(packetNode.isTextual()){
            return new ProtocolType("anon", typeMap.get(packetNode.asText()) != null ? typeMap.get(packetNode.asText()).getFields() : Collections.emptyList());
        }
        throw new RuntimeException("nor object nor array");
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
                    packets.add(new Packet(fieldName, createTypeInstanceFromNOde(packetNode)));
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
/*
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
*/
/*
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
                        if(!Objects.equals(fieldName, "packet")){
                            Packet p = new Packet(fieldName);
                            for (Iterator<String> it = packetsNode.get(fieldName).fieldNames(); it.hasNext(); ) {
                                String field = it.next();
                                //packetsNode.get(field).asText()
                                p.addParam(field, "n");
                                packets.add(p);
                            }
                        }
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
*/
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
    public Map<String, ProtocolType> typesMap(){
        return this.types;
    }
}

