package Serializables.Types;

import Serializables.Refactor.*;
import Serializables.Refactor.RefBuilder.ArgRef;
import Serializables.UnexpectedJsonFormatException;
import Serializables.NativeTypesEnum;

import java.util.*;

public enum FlattenableBuilder {
    CONTAINER("container") {
        @Override
        public Flattenable handle(List<Map<String, Object>> typeJson, boolean stackName) {
            List<ContainerField> containerFields = new ArrayList<>();
            for (Map<String, Object> field : typeJson) {
                Flattenable f = createFlattenable(field, stackName);
                //old (field.get("name")) == null ? "anon" now all switch are anon for field name (quick lazy fix)
                containerFields.add(new ContainerField(f, ((field.get("name")) == null ? "anon" : (String) field.get("name")), stackName));
            }
            return new ContainerBuildable(containerFields.toArray(new ContainerField[0]));
        }
    },
    ARRAY("array") {
        @Override
        public Flattenable handle(Map<String, Object> typeJson, boolean stackName) {
            Flattenable type = createFlattenable(typeJson.get("type"), stackName);
            Object countType = typeJson.get("countType");
            if (countType == null) {
                Object o = typeJson.get("count");
                if (o instanceof Integer i)
                    return new FixedArrayBuildable(i, type);
                return new FieldArrayBuildable((String) o, type);

            } else {
                try {
                    return new PrefArrayBuildable((ClassBuildable) createFlattenable(typeJson.get("countType"), stackName), type);
                } catch (Exception ex) {
                    throw new RuntimeException("Attempting to create array with countType which is not a classBuildable it is:" + createFlattenable(countType, stackName), ex);
                }
            }
        }
    },
    MAPPER("mapper") {
        @Override
        public Flattenable handle(Map<String, Object> typeJson, boolean stackName) {
            Map<String, String> mapperMap = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) (typeJson.get("mappings"))).entrySet()) {
                mapperMap.put(entry.getKey(), (String) entry.getValue());
            }
            try {
                return new MapperBuildable(mapperMap, (ClassBuildable) createFlattenable(typeJson.get("type"), stackName));
            } catch (Exception e) {
                throw new RuntimeException("Attempting to create mapper with a buildable which is not a classBuildable");
            }
        }
    },
    SWITCH("switch") {
        @Override
        public Flattenable handle(Map<String, Object> typeJson, boolean stackName) {
            Map<String, Flattenable> fieldsMap = new HashMap<>();
            for(Map.Entry<String, Object> entry : ((Map<String, Object>)(typeJson.get("fields"))).entrySet()){
                fieldsMap.put(entry.getKey(), createFlattenable(entry.getValue(), stackName));
            }
            if(!typeJson.containsKey("default")){
                return new SwitchBuildable((String)typeJson.get("compareTo"), fieldsMap);
            }
            return new SwitchBuildable((String)typeJson.get("compareTo"), fieldsMap, createFlattenable(typeJson.get("default"), false));

        }
    },
    BUFFER("buffer") {
        @Override
        public Flattenable handle(Map<String, Object> typeJson, boolean stackName) {
            if (typeJson.get("countType") == null) {
                // enter here if not length prefixed, fixed length
                return new FixedBuffer((int) typeJson.get("count"));
            } else {
                return new PrefBuffer((ClassBuildable) createFlattenable(typeJson.get("countType"), stackName));
            }
        }
    },
    OPTION("option") {
        @Override
        public Flattenable handle(Map<String, Object> typeJson, boolean stackName) {
            Flattenable typeD = createFlattenable(typeJson, stackName);
            return new OptionBuildable(typeD);
        }
        @Override
        public Flattenable handle(String typeName, boolean stackName) {
            Flattenable typeD = createFlattenable(typeName, stackName);
            return new OptionBuildable(typeD);
        }
    },
    BITFIELD("bitfield") {
        @Override
        public Flattenable handle(List<Map<String,Object>>  typeJson, boolean stackName) {
            List<BitfieldField> containerFields = new ArrayList<>();
            for(Map<String, Object> field : typeJson){
                containerFields.add(new BitfieldField((Integer) field.get("size"), (Boolean) field.get("signed"), (String) field.get("name")));
            }
            return new BitfieldBuildable(containerFields.toArray(new BitfieldField[0]));
        }
    },
    BITFLAGS("bitflags") {
        @Override
        public Flattenable handle(Map<String, Object> typeJson, boolean stackName) {
            Map<String, String> mapperMap = new LinkedHashMap<>();
            int i = 1;
            for(String entry : (ArrayList<String>) typeJson.get("flags")){
                mapperMap.put(Integer.toString(i), entry);
                i++;
            }
            try {
                return new BitflagsBuildable(mapperMap, (ClassBuildable) createFlattenable(typeJson.get("type"), stackName));
            } catch (Exception e) {
                throw new RuntimeException("Attempting to create bitflags with a type buildable which is not a classBuildable");
            }
        }
    };

    private final String name;
    FlattenableBuilder(String name) {
        this.name = name;
    }
    public Flattenable handle(List<Map<String,Object>> typeJson, boolean stackName) {
        throw new UnsupportedOperationException("Attempting to create a " + name + "with: " + typeJson);
    }
    public Flattenable handle(Map<String,Object> typeJson, boolean stackName){
        throw new UnsupportedOperationException("Attempting to create a " + name + "with: " + typeJson);
    }
    public Flattenable handle(String typeName, boolean stackName){
        throw new UnsupportedOperationException("Attempting to create a " + name + "with: " + typeName);
    }
    private Flattenable handle(Object o,  boolean stackName) {
         switch (o) {
             case Map<?, ?> m : return handle((Map<String,Object>)m, stackName);
            case List<?> l: {
                try {
                    return handle((List<Map<String, Object>>)l, stackName);
                } catch (ClassCastException e) {
                    throw new UnexpectedJsonFormatException("Container with list of Non-Map objects");
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
             case String s: {
                 return handle(s, stackName);
             }
             default : throw new UnexpectedJsonFormatException("Unexpected json format on handler, got: " + o.getClass());
        }
    }


    private static Flattenable createClassBuildableOrKnownType(String s) {
        try {
            NativeTypesEnum aux = NativeTypesEnum.valueOf(s);
            throw new RuntimeException(":(");
      //      return new ClassBuildable(aux.getClazz());
        } catch (Exception e) {
            if(knownTypes.containsKey(s)){
                var aux = knownTypes.get(s).clone();
                return aux == null ? knownTypes.get(s) : aux;
            } else {
                var nullRef = new ArgRef("\"nonDefinedNative-"+ s+ "@FlattenableBuilder\"");
                return new ClassBuildable(Object.class, "Unknown type: " + s, nullRef, nullRef);
             //   throw new RuntimeException("Error unknown (or unbuildable) class: " + s);
//                        return new ClassBuildable(Objects.class, "Error unknown (or unbuildable) class: " + s);
            }
        }
    }
    private static Flattenable flattenableFromBehavioural(List<?> l, boolean stackName) {
        String name = (String) l.getFirst();
        try {
            return valueOf(name.toUpperCase()).handle(l.getLast(), stackName);
        } catch (IllegalArgumentException e) {
            //throw new RuntimeException("Attempting to create a flattenable not defined in enums");
            return new Flattenable() {
                @Override
                public List<PacketField> asPacketFields() {
                    var nullRef = new ArgRef("\"nullNOKNOWNCLASS@FlattenableBuilder\"");
                    return List.of(new PacketField(name, "Unresolved buildable " + name, Object.class, nullRef, nullRef));
                }

                @Override
                public Flattenable clone() {
                    return null;
                }

                @Override
                public String toString() {
                    return "unrecognized behavioural " + name;
                }
            };
        } catch (Exception ex) {
            return new Flattenable() {
                @Override
                public List<PacketField> asPacketFields() {
                    var nullRef = new ArgRef("nullNOKNOWNCLASS");
                    return List.of(new PacketField(name, name + "thew exception: " + ex, Object.class, nullRef, nullRef));
                }

                @Override
                public Flattenable clone() {
                    return null;
                }

                @Override
                public String toString() {
                    return "unrecognized behavioural " + name;
                }
            };
        }
    }

    public static Flattenable createFlattenable(Object o, boolean stackName) {
        switch (o) {
            case List<?> l:
                return flattenableFromBehavioural(l, stackName);
            case Map<?, ?> m:
                return createFlattenable(m.get("type"), stackName);
            case String s:
                return createClassBuildableOrKnownType(s);
            default:
                throw new UnexpectedJsonFormatException("Unexpected json format: " + o);
        }
    }



    private static Map<String, Flattenable> knownTypes = null;

    public static void setKnownTypes(Map<String, Flattenable> knownTypes) {
        if(FlattenableBuilder.knownTypes != null){
            throw new RuntimeException("Attempting to override known types");
        }
        FlattenableBuilder.knownTypes = knownTypes;
    }
}




