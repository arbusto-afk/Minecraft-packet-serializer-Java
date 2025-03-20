package Serializables.Types.Tuples;
import Serializables.Types.ProtocolType;
import java.nio.ByteBuffer;
import java.util.function.Function;

import static Serializables.Types.Tuples.Tuples.*;

public abstract class AbstractTuple implements ProtocolType {
    private static final int TEMP_ALLOC_SIZE = 4096;
    protected final Object[] elements;

    protected AbstractTuple(Object... elements) {
        this.elements = elements;
    }

    @Override
    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(TEMP_ALLOC_SIZE);
        serializeInto(buffer);
        byte[] result = new byte[buffer.position()];
        buffer.flip();
        buffer.get(result);
        return result;
    }

    @Override
    public void serializeInto(ByteBuffer buffer) {
        for (int index = 0; index < elements.length; index++) {
            Object o = elements[index];

            if (o instanceof ProtocolType element) {
                serializeElement(element, buffer);
            } else if (o instanceof ProtocolType[] arr) {
                for (ProtocolType element : arr) {
                    serializeElement(element, buffer);
                }
            } else {
                throw new IllegalArgumentException(
                        "Cannot serialize Tuple at index " + index +
                                ": expected ProtocolType or ProtocolType[], but got " +
                                (o == null ? "null" : o.getClass().getName())
                );
            }
        }
    }

    private void serializeElement(ProtocolType element, ByteBuffer buffer) {
        int startPosition = buffer.position();
        element.serializeInto(buffer);

        if (buffer.position() == startPosition) {
            throw new RuntimeException(
                    "ProtocolType did not write any data: " + element.getClass().getName()
            );
        }

        ensureCapacity(buffer);
    }

    private void ensureCapacity(ByteBuffer buffer) {
        if (buffer.remaining() < 512) { // Adjusted threshold to avoid excessive expansion
            int newCapacity = Math.max(buffer.capacity() * 2, buffer.capacity() + 1024);
            ByteBuffer newBuffer = ByteBuffer.allocate(newCapacity);
            buffer.flip();
            newBuffer.put(buffer);
            buffer.clear();  // Prevent memory leaks
            buffer.put(newBuffer);
        }
    }

    public static AbstractTuple readFrom(ByteBuffer buffer, Function<ByteBuffer, Object>... deserializers) {
        if (deserializers.length <= 1) {
            throw new IllegalArgumentException("Cannot read a tuple with one or zero elements");
        }
        Object[] elements = new Object[deserializers.length];
        for (int i = 0; i < deserializers.length; i++) {
            elements[i] = deserializers[i].apply(buffer);
        }
        return createTupleInstance(elements);
    }

    private static AbstractTuple createTupleInstance(Object[] elements) {
        return switch (elements.length) {
            case 2 -> new Tuple2<>(elements[0], elements[1]);
            case 3 -> new Tuple3<>(elements[0], elements[1], elements[2]);
            case 4 -> new Tuple4<>(elements[0], elements[1], elements[2], elements[3]);
            case 5 -> new Tuple5<>(elements[0], elements[1], elements[2], elements[3], elements[4]);
            case 6 -> new Tuple6<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5]);
            case 7 -> new Tuple7<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6]);
            case 8 -> new Tuple8<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7]);
            case 9 -> new Tuple9<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8]);
            case 10 -> new Tuple10<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9]);
            case 11 -> new Tuple11<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10]);
            case 12 -> new Tuple12<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11]);
            case 13 -> new Tuple13<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12]);
            case 14 -> new Tuple14<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13]);
            case 15 -> new Tuple15<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13], elements[14]);
            case 16 -> new Tuple16<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13], elements[14], elements[15]);
            case 17 -> new Tuple17<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13], elements[14], elements[15], elements[16]);
            case 18 -> new Tuple18<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13], elements[14], elements[15], elements[16], elements[17]);
            case 19 -> new Tuple19<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13], elements[14], elements[15], elements[16], elements[17], elements[18]);
            case 20 -> new Tuple20<>(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12], elements[13], elements[14], elements[15], elements[16], elements[17], elements[18], elements[19]);
            default -> throw new IllegalArgumentException("Unsupported tuple size: " + elements.length);
        };
    }
    public static Class<? extends AbstractTuple> getTupleClassForSize(int size) {
        return switch (size) {
            case 2 -> Tuple2.class;
            case 3 -> Tuple3.class;
            case 4 -> Tuple4.class;
            case 5 -> Tuple5.class;
            case 6 -> Tuple6.class;
            case 7 -> Tuple7.class;
            case 8 -> Tuple8.class;
            case 9 -> Tuple9.class;
            case 10 -> Tuple10.class;
            case 11 -> Tuple11.class;
            case 12 -> Tuple12.class;
            case 13 -> Tuple13.class;
            case 14 -> Tuple14.class;
            case 15 -> Tuple15.class;
            case 16 -> Tuple16.class;
            case 17 -> Tuple17.class;
            case 18 -> Tuple18.class;
            case 19 -> Tuple19.class;
            case 20 -> Tuple20.class;
            default -> throw new IllegalArgumentException("Unsupported tuple size: " + size);
        };
    }
}
