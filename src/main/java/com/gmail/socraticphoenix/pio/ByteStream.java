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

import com.gmail.socraticphoenix.pio.stream.ByteBufferSource;
import com.gmail.socraticphoenix.pio.stream.ByteStreamInputStream;
import com.gmail.socraticphoenix.pio.stream.ByteStreamOutputStream;
import com.gmail.socraticphoenix.pio.stream.DataInSource;
import com.gmail.socraticphoenix.pio.stream.DataOutSource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public interface ByteStream {

    static ByteStream compressing(ByteStream other) throws IOException {
        return ByteStream.of(new GZIPOutputStream(new ByteStreamOutputStream(other)));
    }

    static ByteStream decompressing(ByteStream other) throws IOException {
        return ByteStream.of(new GZIPInputStream(new ByteStreamInputStream(other)));
    }

    static ByteStream of(byte[] array) {
        return ByteStream.of(ByteBuffer.wrap(array));
    }

    static ByteStream of(ByteBuffer buffer) {
        return new ByteBufferSource(buffer);
    }

    static ByteStream of(DataOutputStream stream) {
        return new DataOutSource(stream);
    }

    static ByteStream of(DataInputStream stream) {
        return new DataInSource(stream);
    }

    static ByteStream of(OutputStream stream) {
        if(stream instanceof DataOutputStream) {
            return ByteStream.of((DataOutputStream) stream);
        } else {
            return ByteStream.of(new DataOutputStream(stream));
        }
    }

    static ByteStream of(InputStream stream) {
        if(stream instanceof DataInputStream) {
            return ByteStream.of((DataInputStream) stream);
        } else {
            return ByteStream.of(new DataInputStream(stream));
        }
    }

    int position();

    int remaining();

    void put(byte[] array) throws IOException;

    void get(byte[] array) throws IOException;

    void put(byte b) throws IOException;

    byte get() throws IOException;

    void putInt(int i) throws IOException;

    int getInt() throws IOException;

    void putShort(short s) throws IOException;

    short getShort() throws IOException;

    char getChar() throws IOException;

    void putChar(char c) throws IOException;

    long getLong() throws IOException;

    void putLong(long l) throws IOException;

    float getFloat() throws IOException;

    void putFloat(float f) throws IOException;

    double getDouble() throws IOException;

    void putDouble(double d) throws IOException;

    byte[] toByteArray();

    void close() throws IOException;

}
