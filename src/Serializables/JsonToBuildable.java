package Serializables;

import Serializables.Refactor.BitfieldField;
import Serializables.Complex.ComplexType;
import Serializables.Refactor.*;
import Serializables.Types.jsonDataNameToClassMapper;

import java.util.*;

public class JsonToBuildable {

    private final Map<String, Flattenable[]> knownBuildables;
    private final List<String> unkownBehaviourals;
    public JsonToBuildable(Map<String, Flattenable[]> map, List<String> unkownBehaviourals) {
        this.knownBuildables = map;
        this.unkownBehaviourals = unkownBehaviourals;
    }
    public Flattenable[] createBuildable(Object type){
        switch (type) {
            //object (protocolType or anon switch)
            case Map m: {
                Flattenable[] td = createBuildable(m.get("type"));
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
                        return container2((List<Map<String, Object>>) typeJson).flatten();
                    case "array":
                        return array2((Map<String, Object>) typeJson).flatten();
                    case "mapper": {
                        return  mapper2((Map<String, Object>) typeJson).flatten();
                    }
                    case "switch":
                        return switch2((Map<String, Object>) typeJson).flatten();
                    case "buffer":
                        Map<String, Object> typeMap = (Map<String, Object>) typeJson;
                        if((typeMap.get("countType") == null)) {
                            //enter her if not length prefixed, fixed length
                            return new FixedArrayBuildable((int)typeMap.get("count"), createBuildable("bit")).flatten();
                        } else {
                            return new PrefArrayBuildable((ClassBuildable) createBuildable(typeMap.get("countType"))[0], createBuildable("bit")).flatten();
                        }
                    case "option": {
                        Flattenable[] typeD = createBuildable(typeJson);
                        if(type == null) {
                            var a = 5;
                        }

                        return new OptionBuildable(typeD).flatten();

                    }
                    case "bitfield": {
                        return bitfield2((List<Map<String, Object>>)typeJson).flatten();
                    }
                    case "restBuffer": {
                        return new RestBufferBuildable().flatten();
                    }
                    case "bitflags": {
                        return bitflags((Map<String, Object>)typeJson).flatten();
                    }
                    //        case "pstring":
                    //            return new TypeDescriptor(String.class, "str", new Object[]{});
                    default:
                        final String RESET = "\u001B[0m";
                        final String RED = "\u001B[34m";
                        return new ClassBuildable(ComplexType.class, "Unknown behavioural: " + complexName).flatten();
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

    public Flattenable[] createTypeBuildable(Object type){
        switch (type) {
            //object (protocolType or anon switch)
            case Map m: {
                Flattenable[] td = createBuildable(m.get("type"));
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
                        return container2((List<Map<String, Object>>) typeJson, true).flatten();
                    case "array":
                        return array2((Map<String, Object>) typeJson).flatten();
                    case "mapper": {
                        return  mapper2((Map<String, Object>) typeJson).flatten();
                    }
                    case "switch":
                        return switch2((Map<String, Object>) typeJson).flatten();
                    case "buffer":
                        Map<String, Object> typeMap = (Map<String, Object>) typeJson;
                        if((typeMap.get("countType") == null)) {
                            //enter her if not length prefixed, fixed length
                            return new FixedArrayBuildable((int)typeMap.get("count"), createBuildable("bit")).flatten();
                        } else {
                            return new PrefArrayBuildable((ClassBuildable) createBuildable(typeMap.get("countType"))[0], createBuildable("bit")).flatten();
                        }
                    case "option": {
                        Flattenable[] typeD = createBuildable(typeJson);
                        if(type == null) {
                            var a = 5;
                        }

                        return new OptionBuildable(typeD).flatten();

                    }
                    case "bitfield": {
                        return bitfield2((List<Map<String, Object>>)typeJson).flatten();
                    }
                    case "restBuffer": {
                        return new RestBufferBuildable().flatten();
                    }
                    case "bitflags": {
                        return bitflags((Map<String, Object>)typeJson).flatten();
                    }
                    //        case "pstring":
                    //            return new TypeDescriptor(String.class, "str", new Object[]{});
                    default:
                        final String RESET = "\u001B[0m";
                        final String RED = "\u001B[34m";
                        unkownBehaviourals.add(complexName);
                        return new ClassBuildable(ComplexType.class, "Unknown behavioural: " + complexName).flatten();
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

    public Flattenable container2(List<Map<String,Object>> maps){
        List<ContainerField> containerFields = new ArrayList<>();
        for(Map<String, Object> field : maps) {
            Flattenable[] f = createBuildable(field);
            for (Flattenable subfield : f) {
                containerFields.add(new ContainerField(subfield, ((field.get("name")) == null ? "anon" : (String) field.get("name"))));
            }
        }
        return new ContainerBuildable(containerFields.toArray(new ContainerField[0]));
    }
    public Flattenable container2(List<Map<String,Object>> maps, boolean stackableName){
        List<ContainerField> containerFields = new ArrayList<>();
        for(Map<String, Object> field : maps) {
            Flattenable[] f = createBuildable(field);
            for (Flattenable subfield : f) {
                containerFields.add(new ContainerField(subfield, ((field.get("name")) == null ? "anon" : (String) field.get("name")), stackableName));
            }
        }
        return new ContainerBuildable(containerFields.toArray(new ContainerField[0]));
    }
    public Flattenable bitfield2(List<Map<String,Object>> maps){
        List<BitfieldField> containerFields = new ArrayList<>();
        for(Map<String, Object> field : maps){
            containerFields.add(new BitfieldField((Integer) field.get("size"), (Boolean) field.get("signed"), (String) field.get("name")));
        }
        return new BitfieldBuildable(containerFields.toArray(new BitfieldField[0]));
    }
    public Flattenable array2(Map<String, Object> map) {
        Flattenable[] type = createBuildable(map.get("type"));
        if (map.get("countType") == null) {
            //field
            Object o = map.get("count");
            if (o instanceof Integer i) {
                return new FixedArrayBuildable(i , type);
            } else {
                return new FieldArrayBuildable((String)o, type);
            }
        }
        try {
            return new PrefArrayBuildable((ClassBuildable) createBuildable(map.get("countType"))[0], type);
        } catch (Exception ex) {
            throw new RuntimeException("Attempting to create array with countType which is not a classBuildable it is:" + createBuildable(map.get("countType")), ex);
        }
    }
    //create field from classname or known type on arr;
    private Flattenable[] searchOrCreateField(String s) {
        Flattenable[] b = searchOrDefaultField(s);
        if(b == null) {
            Flattenable[] fieldFlattenable = new Flattenable[]{new ClassBuildable(Objects.class, "Error unknown (or unbuildable) class: " + s)};
            return fieldFlattenable;
        }
        return b.clone();
    }
    //returns mapped class or knownBuildable which can be null
    private Flattenable[] searchOrDefaultField(String s) {
        try {
            return new Flattenable[]{new ClassBuildable(jsonDataNameToClassMapper.getClassOrException(s))};
        } catch (Exception e) {
            return knownBuildables.get(s);
        }
    }
    public Flattenable mapper2(Map<String, Object> map) {
        Map<String, String> mapperMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : ((Map<String, Object>) (map.get("mappings"))).entrySet()) {
            mapperMap.put(entry.getKey(), (String) entry.getValue());
        }
        try {
            return new MapperBuildable(mapperMap, (ClassBuildable) createBuildable(map.get("type"))[0]);
        } catch (Exception e) {
            throw new RuntimeException("Attempting to create mapper with a buildable which is not a classBuildable");
        }
    }
    public Flattenable switch2(Map<String,Object> map){
        Map<String, Flattenable[]> fieldsMap = new HashMap<>();
        for(Map.Entry<String, Object> entry : ((Map<String, Object>)(map.get("fields"))).entrySet()){
            fieldsMap.put(entry.getKey(), createBuildable(entry.getValue()));
        }
        if(!map.containsKey("default")){
            return new SwitchBuildable((String)map.get("compareTo"), fieldsMap);
        }
        return new SwitchBuildable((String)map.get("compareTo"), fieldsMap, createBuildable(map.get("default")));
    }
    private Flattenable bitflags(Map<String, Object> map){
        Map<String, String> mapperMap = new LinkedHashMap<>();
        Integer i = 1;
        for(String entry : ((ArrayList<String>)(map.get("flags")))){
            mapperMap.put(i.toString(), entry);
            i++;
        }
        try {
            return new BitflagsBuildable(mapperMap, (ClassBuildable) createBuildable(map.get("type"))[0]);
        } catch (Exception e) {
            throw new RuntimeException("Attempting to create bitflags with a buildable which is not a classBuildable");
        }
    }

}
