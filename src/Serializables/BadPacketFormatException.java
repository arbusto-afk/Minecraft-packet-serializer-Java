package Serializables;

import java.io.IOException;

public class BadPacketFormatException extends IOException {
    public BadPacketFormatException(String message) {
        super(message);
    }

    public BadPacketFormatException(Exception ex) {
        super(ex);
    }
}
