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
package com.gmail.socraticphoenix.pio.stream;

import com.gmail.socraticphoenix.pio.ByteStream;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class DataInSource implements ByteStream {
    private DataInputStream stream;
    private int position;

    public DataInSource(DataInputStream stream) {
        this.stream = stream;
        this.position = 0;
    }

    @Override
    public int position() {
        return this.position;
    }

    @Override
    public int remaining() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void put(byte[] array) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public void get(byte[] array) throws IOException {
        this.stream.read(array);
        this.position += array.length;
    }

    @Override
    public void put(byte b) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public byte get() throws IOException {
        int i = this.stream.read();
        if(i == -1) {
            throw new EOFException();
        } else {
            this.position += 1;
            return (byte) i;
        }
    }

    @Override
    public void putInt(int i) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public int getInt() throws IOException {
        int i = this.stream.readInt();
        this.position += Integer.BYTES;
        return i;
    }

    @Override
    public void putShort(short s) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public short getShort() throws IOException {
        short s = this.stream.readShort();
        this.position += Short.BYTES;
        return s;
    }

    @Override
    public char getChar() throws IOException {
        char c = this.stream.readChar();
        this.position += Character.BYTES;
        return c;
    }

    @Override
    public void putChar(char c) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public long getLong() throws IOException {
        long l = this.stream.readLong();
        this.position += Long.BYTES;
        return l;
    }

    @Override
    public void putLong(long l) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public float getFloat() throws IOException {
        float f = this.stream.readFloat();
        this.position += Float.BYTES;
        return f;
    }

    @Override
    public void putFloat(float f) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public double getDouble() throws IOException {
        double d = this.stream.readDouble();
        this.position += Double.BYTES;
        return d;
    }

    @Override
    public void putDouble(double d) {
        throw new UnsupportedOperationException("Only get operations are offered by this ByteSource");
    }

    @Override
    public byte[] toByteArray() {
        throw new UnsupportedOperationException("toByteArray is not supported by this ByteSource");
    }

    @Override
    public void close() throws IOException {
        this.stream.close();
    }

}
