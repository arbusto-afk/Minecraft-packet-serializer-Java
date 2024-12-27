    package neoutil;
    
    import java.util.List;
    
    public class PacketField {
        private String name;
        private String type;
        private Boolean optional;
        private List<PacketField> types;
    
        public PacketField(String type, String name) {
            this.type = type;
            this.name = name;
            this.optional = false;
        }
        public PacketField(List<PacketField> types, String name){
            this.types = types;
            this.name = name;
        }
    
        public PacketField(String name, String type, Boolean optional) {
            this.name = name;
            this.type = type;
            this.optional = optional;
        }
        public PacketField(String name, List<PacketField> type, Boolean optional) {
            this.name = name;
            this.types = type;
            this.optional = optional;
        }
    
        @Override
        public String toString() {
            return "<" + name +
                    "(" + (type != null ? type : types) + ')' +
                    '>';
        }
    }
