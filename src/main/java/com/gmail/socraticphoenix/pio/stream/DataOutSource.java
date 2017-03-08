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

import java.io.DataOutputStream;
import java.io.IOException;

public class DataOutSource implements ByteStream {
    private DataOutputStream stream;
    private int position;

    public DataOutSource(DataOutputStream stream) {
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
    public void put(byte[] array) throws IOException {
        this.stream.write(array);
        this.position += array.length;
    }

    @Override
    public void get(byte[] array) {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void put(byte b) throws IOException {
        this.stream.write(b);
        this.position++;
    }

    @Override
    public byte get() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void putInt(int i) throws IOException {
        this.stream.writeInt(i);
        this.position += Integer.BYTES;
    }

    @Override
    public int getInt() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void putShort(short s) throws IOException {
        this.stream.writeShort(s);
        this.position += Short.BYTES;
    }

    @Override
    public short getShort() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void putChar(char c) throws IOException {
        this.stream.writeChar(c);
        this.position += Character.BYTES;
    }

    @Override
    public char getChar() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void putLong(long l) throws IOException {
        this.stream.writeLong(l);
        this.position += Long.BYTES;
    }

    @Override
    public long getLong() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void putFloat(float f) throws IOException {
        this.stream.writeFloat(f);
        this.position += Float.BYTES;
    }

    @Override
    public float getFloat() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
    }

    @Override
    public void putDouble(double d) throws IOException {
        this.stream.writeDouble(d);
        this.position += Double.BYTES;
    }

    @Override
    public double getDouble() {
        throw new UnsupportedOperationException("Only put operations are offered by this ByteSource");
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
