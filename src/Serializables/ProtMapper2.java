package Serializables;

import Serializables.Complex.Container;
import Serializables.Complex.Void;
import Serializables.Refactor.*;
import Serializables.Types.VarInt;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import Serializables.Complex.*;
import Serializables.Types.PrimitiveMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.rmi.NoSuchObjectException;
import java.util.*;

public class ProtMapper2 {



    private static final Map<String, TypeDescriptor> types = new LinkedHashMap<>();
    private static final Map<String, Buildable> newTypes = new LinkedHashMap<>();
    private final Map<String, Map<String, Map<String, PacketV2>>> packets = new LinkedHashMap<>();
    @JsonProperty("types")
    public void setTypes(Map<String, Object> types) {
    /*    this.types.put("void", new TypeDescriptor(Void.class, "void"));
        for(PrimitiveMapper p : PrimitiveMapper.values()){
            this.types.put(p.name(), new TypeDescriptor(PrimitiveMapper.getClassOrException(p.name()), p.name(), new Object[]{}));
        }
        for (Map.Entry<String, Object> entry : types.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!this.types.containsKey(key)) {
                if (false) {
                    this.types.put(key, fieldFromMappedJson(key));
                } else {
                    this.types.put(key, createTypeDescriptorFromJsonObject(value));
                }
            }
            System.out.println("Loaded Type: " + key + " -> \n<" + this.types.get(key).getBuilder() + ">");
        }*/
        this.newTypes.put("void", new ClassBuildable(Void.class));
        for(PrimitiveMapper p : PrimitiveMapper.values()){
            this.newTypes.put(p.name(), new ClassBuildable(PrimitiveMapper.getClassOrException(p.name())));
        }
        for(Map.Entry<String, Object> entry : types.entrySet()) {
            if(!this.newTypes.containsKey(entry.getKey())) {
                this.newTypes.put(entry.getKey(), createBuildable(entry.getValue()));
                System.out.println("Loaded Type: " + entry.getKey() + " -> \n<" + this.newTypes.get(entry.getKey()).getBuildable() + ">");
            }
        }
    }


    public static TypeDescriptor createTypeDescriptorFromJsonObject(Object type) {
        switch (type) {
            //object (protocolType or anon switch)
            case Map m: {
                TypeDescriptor td = createTypeDescriptorFromJsonObject(m.get("type"));
                if(m.get("name") != null) {
                    td.SetDesc((String) m.get("name"));
     //               System.out.println("Setting desc for " + m.get("type") + "to:" + m.get("name"));
                }
                return td;
            }
            //complex container, switch, arr
            case List l: {
                String complexName = (String) l.getFirst();
                Object typeJson = l.get(1);
                switch (complexName) {
                    case "container":
                        return containerFromMappedJson((List<Map<String, Object>>) typeJson);
                    case "array":
                        return arrayFromMappedJson((Map<String, Object>) typeJson);
                    case "mapper": {
                        return  mapperFromMappedJson((Map<String, Object>) typeJson);
                    }
                    case "switch":
                        return switchFromMappedJson((Map<String, Object>) typeJson);
                    case "buffer":
                        if(((Map<String, Object>)typeJson).get("countType") == null) {
                        //enter her if not length prefixed, fixed length
                            return new TypeDescriptor(Array.class, complexName, new Object[]{(int)(((Map<String, Object>)typeJson).get("count")), fieldFromMappedJson("bit")});
                        } else {
                            return new TypeDescriptor(Array.class, complexName, new Object[]{fieldFromMappedJson((String)((Map<String, Object>)typeJson).get("countType")), fieldFromMappedJson("bit")});
                        }
                    case "option": {
                        TypeDescriptor typeD = createTypeDescriptorFromJsonObject(typeJson);
                        if(type == null) {
                            var a = 5;
                        }
                        return new TypeDescriptor(Option.class, complexName, new Object[]{typeD});
                    }
                    case "pstring":
                        return new TypeDescriptor(String.class, "str", new Object[]{});
                    case "bitfield":
                        return bitfieldFromMappedJson(((List<Map<String, Object>>) typeJson));
                    default:
                        final String RESET = "\u001B[0m";
                        final String RED = "\u001B[34m";
                        return new TypeDescriptor(ComplexType.class, "defaulted:" + complexName);
                }
            }
            case String s: {
              //  if(types.get(s) != null && !types.get(s).equals("native"))
             //       return types.get(s);
                return fieldFromMappedJson(s);

            }
            default:
                throw new UnexpectedJsonFormatException(type);
        }
    }
    public static TypeDescriptor createTypeDescriptorFromJsonObjectWithExistingTypes(Object type) {
        switch (type) {
            //object (protocolType or anon switch)
            case Map m: {
                TypeDescriptor td = createTypeDescriptorFromJsonObject(m.get("type"));
                if(m.get("name") != null) {
                    td.SetDesc((String) m.get("name"));
                    //               System.out.println("Setting desc for " + m.get("type") + "to:" + m.get("name"));
                }
                return td;
            }
            //complex container, switch, arr
            case List l: {
                String complexName = (String) l.getFirst();
                Object typeJson = l.get(1);
                switch (complexName) {
                    case "container":
                        return containerFromMappedJson((List<Map<String, Object>>) typeJson);
                    case "array":
                        return arrayFromMappedJson((Map<String, Object>) typeJson);
                    case "mapper": {
                        return  mapperFromMappedJson((Map<String, Object>) typeJson);
                    }
                    case "switch":
                        return switchFromMappedJson((Map<String, Object>) typeJson);
                    case "buffer":
                        if(((Map<String, Object>)typeJson).get("countType") == null) {
                            //enter her if not length prefixed, fixed length
                            return new TypeDescriptor(Array.class, complexName, new Object[]{(((Map<String, Object>)typeJson).get("count")), fieldFromMappedJson("bit")});
                        } else {
                            return new TypeDescriptor(Array.class, complexName, new Object[]{fieldFromMappedJson("varint"), fieldFromMappedJson("bit")});
                        }
                    case "option": {
                        TypeDescriptor typeD = createTypeDescriptorFromJsonObject(typeJson);
                        if(type == null) {
                            var a = 5;
                        }
                        return new TypeDescriptor(Option.class, complexName, new Object[]{typeD});
                    }
                    case "pstring":
                        return new TypeDescriptor(String.class, "str", new Object[]{});
                    case "bitfield":
                        return bitfieldFromMappedJson(((List<Map<String, Object>>) typeJson));
                    default:
                        final String RESET = "\u001B[0m";
                        final String RED = "\u001B[34m";
                        return new TypeDescriptor(ComplexType.class, "defaulted:" + complexName);
                }
            }
            case String s: {
                 if(types.get(s) != null && !types.get(s).equals("native"))
                       return types.get(s);
                return fieldFromMappedJson(s);

            }
            default:
                throw new UnexpectedJsonFormatException(type);
        }
    }

    private static TypeDescriptor containerFromMappedJson(List<Map<String, Object>> maps){
        List<TypeDescriptor> subFields = new ArrayList<>();
        for(Map<String, Object> field : maps){
            subFields.add(createTypeDescriptorFromJsonObject(field));
        }
        return new TypeDescriptor(Container.class, "Container", subFields.toArray());
    }
    private static TypeDescriptor arrayFromMappedJson(Map<String,Object> map){
        TypeDescriptor type = createTypeDescriptorFromJsonObject(map.get("type"));
        if(map.get("countType") == null){
            //count is a field unsupported currently
            return new TypeDescriptor(fArray.class, "fArray", new Object[]{(String)map.get("count"), type});
        }
        //should be field so i get[0]
    //    TypeDescriptor countType = createTypeDescriptorFromJsonObject(map.get("countType")).getBuilder().getFirst();
        return new TypeDescriptor(Array.class, "Array", new Object[]{fieldFromMappedJson((String)map.get("countType")), type});
    }
    private static TypeDescriptor mapperFromMappedJson(Map<String, Object> map){
        return new TypeDescriptor(Mapper.class, "Mapper", new Object[]{createTypeDescriptorFromJsonObject(map.get("type"))});
    }
    private static TypeDescriptor switchFromMappedJson(Map<String, Object> map){
        Map<String, TypeDescriptor> fieldsMap = new HashMap<>();
        for(Map.Entry<String, Object> entry : ((Map<String, Object>)(map.get("fields"))).entrySet()){
            fieldsMap.put(entry.getKey(), createTypeDescriptorFromJsonObject(entry.getValue()));
        }
        if(map.containsKey("default")){
            return new TypeDescriptor(Switch.class, "Switch", new Object[]{map.get("compareTo"), fieldsMap, createTypeDescriptorFromJsonObject(map.get("default"))});
        }
        return new TypeDescriptor(Switch.class, "Switch", new Object[]{map.get("compareTo"), fieldsMap});
    }
    private static TypeDescriptor fieldFromMappedJson(String s){

                if(types.get(s) != null) {
                    return types.get(s).duplicate();
                }
                if(s.equals("void")){
                    return new TypeDescriptor(Void.class, "void");
                }

                 final String RESET = "\u001B[0m";
                 final String RED = "\u001B[31m";
        if(s.equals("position")) {
            return new TypeDescriptor(Object.class, RED + "UNRPD_" + s + RESET);
        }
        return new TypeDescriptor(Object.class, RED + "UNRPD_" + s + RESET);

    }
    private static TypeDescriptor bitfieldFromMappedJson(List<Map<String, Object>> l){
        List<TypeDescriptor> comps = new ArrayList<>();
        for(Map<String, Object> field : l){
            TypeDescriptor bFieldComponent = new TypeDescriptor(BitfieldComponent.class, "BFcomp", new Object[]{ field.get("size"), field.get("signed"), (String)field.get("name")});
            comps.add(bFieldComponent);
        }
        TypeDescriptor[] arr = comps.toArray(new TypeDescriptor[comps.size()]);
        return new TypeDescriptor(Container.class, "bitField", arr);
    }

    private static Buildable createBuildable(Object type){
        switch (type) {
            //object (protocolType or anon switch)
            case Map m: {
                Buildable td = createBuildable(m.get("type"));
                if(m.get("name") != null) {
             //       td.SetDesc((String) m.get("name"));
                    //               System.out.println("Setting desc for " + m.get("type") + "to:" + m.get("name"));
                }
                return td;
            }
            //complex container, switch, arr
            case List l: {
                String complexName = (String) l.getFirst();
                Object typeJson = l.get(1);
                switch (complexName) {
                    case "container":
                        return container2((List<Map<String, Object>>) typeJson);
                    case "array":
                        return array2((Map<String, Object>) typeJson);
                    case "mapper": {
                        return  mapper2((Map<String, Object>) typeJson);
                    }
                    case "switch":
                        return switch2((Map<String, Object>) typeJson);
                    case "buffer":
                        Map<String, Object> typeMap = (Map<String, Object>) typeJson;
                        if((typeMap.get("countType") == null)) {
                            //enter her if not length prefixed, fixed length
                            return new FixedArrayBuildable((int)typeMap.get("count"), createBuildable("bit"));
                        } else {
                            return new ArrayBuildable(createBuildable(typeMap.get("countType")), createBuildable("bit"));
                        }
                    case "option": {
                        Buildable typeD = createBuildable(typeJson);
                        if(type == null) {
                            var a = 5;
                        }
                        return new OptionBuildable(typeD);
                    }
            //        case "pstring":
            //            return new TypeDescriptor(String.class, "str", new Object[]{});
                    case "bitfield":
                        return bitField2(((List<Map<String, Object>>) typeJson));
                    default:
                        final String RESET = "\u001B[0m";
                        final String RED = "\u001B[34m";
                        return new ClassBuildable(ComplexType.class);
                }
            }
            case String s: {
                //  if(types.get(s) != null && !types.get(s).equals("native"))
                //       return types.get(s);
                return field2(s);
            }
            default:
                throw new UnexpectedJsonFormatException(">>>" + type + " of class:" +type.getClass()+"<<<");
        }
    }

    private static Buildable bitField2(List<Map<String, Object>> l){
        List<Buildable> fields = new ArrayList<>();
        for(Map<String, Object> field : l){
            Buildable bFieldComponent = new BitfieldComponent((int)field.get("size"), (boolean)field.get("signed"), (String)field.get("name"));
            fields.add(bFieldComponent);
        }
        return new ContainerBuildable(fields.toArray(new Buildable[0]));
    }
    private static Buildable container2(List<Map<String,Object>> maps){
        List<Buildable> containerFields = new ArrayList<>();
        for(Map<String, Object> field : maps){
            containerFields.add(createBuildable(field));
        }
        return new ContainerBuildable(containerFields.toArray(new Buildable[0]));
    }
    private static Buildable array2(Map<String, Object> map){
        Buildable type = createBuildable(map.get("type"));
        if(map.get("countType") == null) {
            //field
            return new FieldArrayBuildable((String)map.get("count"), type);
        }
        return new ArrayBuildable(createBuildable(map.get("countType")), type);
    }
    //create field from classname or known type on arr;
    private static Buildable field2(String s) {
        try {
            return new ClassBuildable(PrimitiveMapper.getClassOrException(s));
        } catch (Exception e) {
            System.err.println("Could not find class for " + s);
            return new ClassBuildable(Object.class);
        }
    }
    private static Buildable mapper2(Map<String, Object> map){
        Map<String, String> mapperMap = new LinkedHashMap<>();
        for(Map.Entry<String, Object> entry : ((Map<String,Object>)(map.get("mappings"))).entrySet()){
            mapperMap.put(entry.getKey(), (String)map.get(entry.getKey()));
        }
        return new MapperBuildable(mapperMap, createBuildable(map.get("type")));
    }
    private static Buildable switch2(Map<String,Object> map){
        Map<String, Buildable> fieldsMap = new HashMap<>();
        for(Map.Entry<String, Object> entry : ((Map<String, Object>)(map.get("fields"))).entrySet()){
            fieldsMap.put(entry.getKey(), createBuildable(entry.getValue()));
        }
        if(!map.containsKey("default")){
            return new SwitchBuildable((String)map.get("compareTo"), fieldsMap);
        }
        return new SwitchBuildable((String)map.get("compareTo"), fieldsMap, createBuildable(map.get("default")));
    }

    public Map<String, TypeDescriptor> getTypes() {
        return types;
    }

    private void forEachPacket(String name, Object contents, String state, String target){
       @SuppressWarnings("unchecked")
       Object fields = ((ArrayList<Object>) contents).getLast();
       List<String> fieldClassesName = new ArrayList<>();

       TypeDescriptor t = createTypeDescriptorFromJsonObjectWithExistingTypes(contents);


       PacketV2 p = new PacketV2(name, t );
        packets.get(state).get(target).put(name, p);
        System.out.println("Loaded packet: " + name + " -> \n<" + p.getBuilder() + ">");

    }
    @JsonAnySetter
    public void addPacket(String key, Object value) {
        // Any JSON key that isn't "types" will be treated as a packet
/*
        //to server to client
        if (!packets.containsKey(key))
            packets.put(key, new LinkedHashMap<>());
        if (!(value instanceof Map<?, ?> toMap)) {
            throw new UnexpectedJsonFormatException(key);
        }
        //for toClient / toPacket
        for (Map.Entry<?, ?> subMap : toMap.entrySet()) {

            if (!packets.get(key).containsKey(subMap.getKey())) {
                packets.get(key).put(subMap.getKey().toString(), new LinkedHashMap<>());
            }
            if (!(subMap.getValue() instanceof Map<?, ?> destMap)) {
                throw new RuntimeException("Unsupported format for packet: " + subMap.getValue());
            }
            Map<?, ?> packetMap = (Map<?, ?>) destMap.get("types");
            for (Map.Entry<?, ?> specificPacketMap : packetMap.entrySet()) {
                String packetName = specificPacketMap.getKey().toString();
                Object contents = specificPacketMap.getValue();
                // PacketV2 p = new PacketV2(packetName, createComplexTypeFromJsonObject(packetType));
                //todo improve this
                if (packetName.equals("packet")) {
                     /*     //  packets.get(key).get(subMap.getKey()).put(p.getName(), p);
                            String mapper = p.getFields().get(0).getType();
                            String regex = "0x([0-9a-fA-F]+)=([\\w_]+)";
                            Pattern pattern = Pattern.compile(regex);

                            // Process each input string
                            for (String input : Arrays.asList(mapper)) {
                                Matcher matcher = pattern.matcher(input);
                                //  System.out.println("Mappings:");
                                while (matcher.find()) {
                                    String id = "0x" + matcher.group(1);
                                    String name = matcher.group(2);
                                    //    System.out.println("ID: " + id + ", Name: " + name);
                                    if (packets.get(key).get(subMap.getKey()).get("packet_" + name) != null) {
                                        packets.get(key).get(subMap.getKey()).get("packet_" + name).setId(Integer.decode(id));
                                    }
                                }
                                //     System.out.println();
                            } aca cierro anidado pero no me deja asi que comento
                        * /
                } else {

                    //    packets.get(key).get(subMap.getKey()).put(p.getName(), p);
                    forEachPacket(packetName, contents, key,(String)subMap.getKey());
                }

            }
        }
        */

    }


    public List<PacketV2> getPackets() {
        return packets.values().stream()
                .flatMap(level1 -> level1.values().stream())  // Flatten to second-level maps
                .flatMap(level2 -> level2.values().stream())  // Flatten to Packet objects
                .toList();                                   // Collect into a list
    }
    public Object buildAll(){
        Map<String,Object> arr = new LinkedHashMap<>();
        for(Map.Entry<String, TypeDescriptor> entry : types.entrySet()) {
            try {
                arr.put(entry.getKey(), entry.getValue().getBuilder());
            } catch (Exception e) {
                System.out.println("Erro|r building: " + entry.getKey());
            }
        }
        return arr;
    }

    public PacketV2 getPacket(String state, String clientboundOrServerbound, String name) {
        if(!packets.containsKey(state)) {
            throw new NoSuchElementException("State not found: " + state);
        }
        if(packets.get(state).get(clientboundOrServerbound).containsKey(name)) {
            throw new NoSuchElementException("packet '" + name +"' not found: ");
        }
        return packets.get(state).get(clientboundOrServerbound).get(name);
    }

}
