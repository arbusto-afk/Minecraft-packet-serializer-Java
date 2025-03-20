package Serializables.Refactor;

import Serializables.Refactor.RefBuilder.RefBuilder;
import Serializables.Refactor.RefBuilder.TernaryRef;

import java.sql.Ref;
import java.util.Objects;

public class PacketField {
    private String name;
    private final String desc;
    private final SSRB ssrb;
    private final RefBuilder deserializerMethod;
    private final RefBuilder serializerMethod;

    public PacketField(String name, String desc, SSRB ssrb, RefBuilder deserializerMethod, RefBuilder serializerMethod) {
        this.name = name;
        this.desc = desc;
        this.ssrb = ssrb;
        this.deserializerMethod = deserializerMethod;
        this.serializerMethod = serializerMethod;
    }
    public PacketField(String name, String desc, Class<?> clazz, RefBuilder deserializerMethod, RefBuilder serializerMethod) {
        this(name, desc, new SSRB(clazz), deserializerMethod, serializerMethod);
    }

    private boolean hasSkippedFirstField = false;

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", ssrb=" + ssrb +
                ", deserializerMethod=" + deserializerMethod +
                ", serializerMethod=" + serializerMethod +
                ", hasSkippedFirstField=" + hasSkippedFirstField
                ;
    }

    public PacketField prefixName(String name) {
        if(name.equals("anon")) {
            hasSkippedFirstField = true;
            return this;
        }
        this.name = this.name.isEmpty() ? name : name + "_" + this.name;
        deserializerMethod.prefixName(name);
        return this;

    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public SSRB getSsrb() {
        return ssrb;
    }

    public RefBuilder getDeserializerMethod() {
        return deserializerMethod;
    }

    public RefBuilder getSerializerMethod() {
        return serializerMethod;
    }

    @Override
    public boolean equals(Object o){
        // Check if the object is being compared to itself
        if (this == o) return true;

        // Check if the object is null or not an instance of ClassBuildable
        if (o == null || getClass() != o.getClass()) return false;

        // Cast the object to ClassBuildable
        PacketField that = (PacketField) o;

        // Compare the clazz fields
        return ssrb != null ? ssrb.equals(that.getSsrb()) : that.getSsrb() == null;
    }
    @Override
    public int hashCode() {
        // Use the hashCode of the clazz field
        return Objects.hash(ssrb);
    }
}
