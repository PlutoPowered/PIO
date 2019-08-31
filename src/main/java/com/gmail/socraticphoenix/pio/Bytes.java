/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 socraticphoenix@gmail.com
 * Copyright (c) 2016 contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.gmail.socraticphoenix.pio;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Bytes {

    public static void writeArray(ByteStream buffer, byte[] array) throws IOException {
        if (array == null) {
            buffer.putInt(-1);
        } else if (array.length == 0) {
            buffer.putInt(0);
        } else {
            buffer.putInt(array.length);
            buffer.put(array);
        }
    }

    public static byte[] readArray(ByteStream buffer) throws IOException {
        int len = buffer.getInt();
        if (len == -1) {
            return null;
        } else if (len == 0) {
            return new byte[0];
        } else {
            byte[] array = new byte[len];
            buffer.get(array);
            return array;
        }
    }

    public static int length(byte[] array) {
        return (array != null ? array.length : 0) + Integer.BYTES;
    }

    public static void writeBigDecimal(ByteStream buffer, BigDecimal decimal) throws IOException {
        Bytes.writeBigInt(buffer, decimal.unscaledValue());
        buffer.putInt(decimal.scale());
    }

    public static BigDecimal readBigDecimal(ByteStream buffer) throws IOException {
        return new BigDecimal(Bytes.readBigInt(buffer), buffer.getInt());
    }

    public static int length(BigDecimal decimal) {
        return Bytes.length(decimal.unscaledValue()) + Integer.BYTES;
    }

    public static void writeBigInt(ByteStream buffer, BigInteger integer) throws IOException {
        Bytes.writeArray(buffer, integer == null ? null : integer.toByteArray());
    }

    public static BigInteger readBigInt(ByteStream buffer) throws IOException {
        return new BigInteger(Bytes.readArray(buffer));
    }

    public static int length(BigInteger integer) {
        return Bytes.length(integer == null ? null : integer.toByteArray());
    }

    public static void writeString(ByteStream buffer, String s) throws IOException {
        Bytes.writeArray(buffer, s == null ? null : s.getBytes(StandardCharsets.UTF_8));
    }

    public static String readString(ByteStream buffer) throws IOException {
        byte[] array = Bytes.readArray(buffer);
        return array == null ? null : new String(array, StandardCharsets.UTF_8);
    }

    public static int length(String s) {
        return Bytes.length(s == null ? null : s.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] compress(byte[] array) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gos = new GZIPOutputStream(bos);
            gos.write(array);
            gos.close();
            bos.close();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("ByteArrayOutputStream generated IOException", e);
        }
    }

    public static byte[] decompress(byte[] array) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        GZIPInputStream gis = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int i = 0;
        while ((i = gis.read()) != -1) {
            bos.write(i);
        }
        return bos.toByteArray();
    }

    public static String readAllText(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int c;
        while ((c = reader.read()) != -1) {
            builder.appendCodePoint(c);
        }
        return builder.toString();
    }

}
