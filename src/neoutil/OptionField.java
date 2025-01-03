package neoutil;

import java.util.List;

public class OptionField extends PacketField{
    public OptionField(String type, String name, List<PacketField> subFields) {
        super(type, name, subFields);
    }
}
