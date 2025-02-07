package Serializables;

import Serializables.Refactor.BitfieldComponent;
import Serializables.Complex.ComplexType;
import Serializables.Refactor.*;
import Serializables.Types.PrimitiveMapper;

import java.util.*;

public class JsonToBuildable {

    private final Map<String, Buildable> knownBuildables;
    public JsonToBuildable(Map<String,Buildable> map){
        this.knownBuildables = map;
    }
    public Buildable createBuildable(Object type){
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
                            return new PrefArrayBuildable((ClassBuildable) createBuildable(typeMap.get("countType")), createBuildable("bit"));
                        }
                    case "option": {
                        Buildable typeD = createBuildable(typeJson);
                        if(type == null) {
                            var a = 5;
                        }

                        return new OptionBuildable(typeD);

                    }
                    case "restBuffer": {
                        return new RestBufferBuildable();
                    }
                    case "bitflags": {
                        return bitflags((Map<String, Object>)typeJson);
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
                return searchOrCreateField(s);
            }
            default:
                throw new UnexpectedJsonFormatException(">>>" + type + " of class:" +type.getClass()+"<<<");
        }
    }

    public  Buildable bitField2(List<Map<String, Object>> l){
        List<BitfieldComponent> fields = new ArrayList<>();
        for(Map<String, Object> field : l){
            BitfieldComponent bFieldComponent = new BitfieldComponent((int)field.get("size"), (boolean)field.get("signed"), (String)field.get("name"));
            fields.add(bFieldComponent);
        }
        return new ContainerBuildable(fields.toArray(new BitfieldComponent[0]));
    }
    public  Buildable container2(List<Map<String,Object>> maps){
        List<ContainerField> containerFields = new ArrayList<>();
        for(Map<String, Object> field : maps){
            containerFields.add(new ContainerField(createBuildable(field), ((field.get("name")) == null ? "anon": (String) field.get("name"))));
        }
        return new ContainerBuildable(containerFields.toArray(new ContainerField[0]));
    }
    public  Buildable array2(Map<String, Object> map) {
        Buildable type = createBuildable(map.get("type"));
        if (map.get("countType") == null) {
            //field
            return new FieldArrayBuildable((String) map.get("count"), type);
        }
        try {
            return new PrefArrayBuildable((ClassBuildable) createBuildable(map.get("countType")), type);
        } catch (Exception ex) {
            throw new RuntimeException("Attempting to create array with countType which is not a classBuildable it is:" + createBuildable(map.get("countType")), ex);
        }
    }
    //create field from classname or known type on arr;
    private Buildable searchOrCreateField(String s) {
        Buildable b = searchOrDefaultField(s);
        if(b == null) {
            Buildable fieldBuildable = new ClassBuildable(Objects.class);
            return fieldBuildable;
        }
        return b.clone();
    }
    private Buildable searchOrDefaultField(String s) {
        try {
            return new ClassBuildable(PrimitiveMapper.getClassOrException(s));
        } catch (Exception e) {
            return knownBuildables.get(s);
        }
    }
    public Buildable mapper2(Map<String, Object> map) {
        Map<String, String> mapperMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : ((Map<String, Object>) (map.get("mappings"))).entrySet()) {
            mapperMap.put(entry.getKey(), (String) entry.getValue());
        }
        try {
            return new MapperBuildable(mapperMap, (ClassBuildable) createBuildable(map.get("type")));
        } catch (Exception e) {
            throw new RuntimeException("Attempting to create mapper with a buildable which is not a classBuildable");
        }
    }
    public Buildable switch2(Map<String,Object> map){
        Map<String, Buildable> fieldsMap = new HashMap<>();
        for(Map.Entry<String, Object> entry : ((Map<String, Object>)(map.get("fields"))).entrySet()){
            fieldsMap.put(entry.getKey(), createBuildable(entry.getValue()));
        }
        if(!map.containsKey("default")){
            return new SwitchBuildable((String)map.get("compareTo"), fieldsMap);
        }
        return new SwitchBuildable((String)map.get("compareTo"), fieldsMap, createBuildable(map.get("default")));
    }
    private Buildable bitflags(Map<String, Object> map){
        Map<String, String> mapperMap = new LinkedHashMap<>();
        Integer i = 1;
        for(String entry : ((ArrayList<String>)(map.get("flags")))){
            mapperMap.put(i.toString(), entry);
            i++;
        }
        try {
            return new BitflagsBuildable(mapperMap, (ClassBuildable) createBuildable(map.get("type")));
        } catch (Exception e) {
            throw new RuntimeException("Attempting to create bitflags with a buildable which is not a classBuildable");
        }
    }

}
