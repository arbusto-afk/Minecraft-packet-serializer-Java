package Serializables;

import Serializables.Types.*;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.function.IntFunction;

public class ProtocolDeserializers {
    public static String readVarintPrefixedString(ByteBuffer buffer) {
        VarInt length = VarInt.readFrom(buffer);
        byte[] bytes = new byte[length.asInt()];
        buffer.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8); // Convert to string
    }
    public static <T> T[] readVarintPrefixedArr(ByteBuffer buffer, Function<Object, T> f, IntFunction<T[]> arrayConstructor) {
        VarInt length = VarInt.readFrom(buffer);
        T[] arr = arrayConstructor.apply(length.asInt());
        for(int i = 0; i < length.asInt(); i++) {
            arr[i] = f.apply(buffer);
        }
        return arr;
    }
    public static <T> T[] readFixedArr(ByteBuffer buffer, Function<Object, T> f, IntFunction<T[]> arrayConstructor, int size) {
        T[] arr = arrayConstructor.apply(size);
        for(int i = 0; i < size; i++) {
            arr[i] = f.apply(buffer);
        }
        return arr;
    }
    public static boolean readProtocolBool(ByteBuffer buffer) {
        return buffer.get() != 0;
    }
    public static String readProtocolString(ByteBuffer buffer) {
        return "non implemented yet";
    }
    public static Long readVarLong(ByteBuffer buffer) {
        long result = 0;
        int shift = 0;
        byte currentByte;
        do {
            currentByte = buffer.get();
            result |= (long) (currentByte & 0x7F) << shift;
            shift += 7;
            if (shift > 70) {
                throw new IllegalArgumentException("VarLong is too big.");
            }
        } while ((currentByte & 0x80) != 0);
        return result;
    }
    public static Integer readVarInt(ByteBuffer buffer) {
        int result = 0;
        int shift = 0;
        byte currentByte;
        do {
            currentByte = buffer.get();
            result |= (currentByte & 0x7F) << shift;
            shift += 7;
            if (shift > 35) {
                throw new IllegalArgumentException("VarInt is too big.");
            }
        } while ((currentByte & 0x80) != 0);
        return result;
    }
    public static BigInteger readUUID(ByteBuffer buffer) {
        byte[] bytes = new byte[16];
        buffer.get(bytes);
        return new BigInteger(1, bytes);
    }
    public static BigInteger readU64(ByteBuffer buffer) {
        long longValue = buffer.getLong();
        return (longValue >= 0 ? BigInteger.valueOf(longValue) : BigInteger.valueOf(longValue).add(BigInteger.ONE.shiftLeft(64)));
    }
    public static Long readU32(ByteBuffer buffer) {
        return (buffer.getInt() & 0xFFFFFFFFL);
    }
    public static Integer readU16(ByteBuffer buffer) {
        return (buffer.getShort() & 0xFFFF);
    }
    public static Short readU8(ByteBuffer buffer) {
        return (short)(buffer.get() & 0xFF);
    }
    public static boolean readBool(ByteBuffer buffer) {
        return buffer.get() != 0;
    }
    public static String readString(ByteBuffer buffer) {
        // Read the length prefix as a VarInt
        VarInt length = VarInt.readFrom(buffer);

        // Read the string bytes
        byte[] stringBytes = new byte[length.asInt()];
        buffer.get(stringBytes);

        // Convert the bytes to a string
        String value = new String(stringBytes, StandardCharsets.UTF_8);
        return value;
    }
    public static Long readI64(ByteBuffer buffer) {
        return (buffer.getLong());
    }
    public static Integer readI32(ByteBuffer buffer) {
        return (buffer.getInt());
    }
    public static Short readI16(ByteBuffer buffer) {
        return (buffer.getShort());
    }
    public static Byte readI8(ByteBuffer buffer) {
        return (buffer.get());
    }
    public static Double readF64(ByteBuffer buffer) {
        return (buffer.getDouble());
    }
    public static Float readF32(ByteBuffer buffer) {
        return (buffer.getFloat());
    }

    public static long peekI64(ByteBuffer buf) {
        int pos = buf.position();
        long value = buf.getLong();
        buf.position(pos);
        return value;
    }

    public static int peekI32(ByteBuffer buf) {
        int pos = buf.position();
        int value = buf.getInt();
        buf.position(pos);
        return value;
    }

    public static short peekI16(ByteBuffer buf) {
        int pos = buf.position();
        short value = buf.getShort();
        buf.position(pos);
        return value;
    }

    public static byte peekI8(ByteBuffer buf) {
        int pos = buf.position();
        byte value = buf.get();
        buf.position(pos);
        return value;
    }



}
