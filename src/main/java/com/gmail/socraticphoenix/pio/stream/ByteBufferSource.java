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

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferSource implements ByteStream {
    private ByteBuffer buffer;

    public ByteBufferSource(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public int position() {
        return this.buffer.position();
    }

    @Override
    public int remaining() {
        return this.buffer.remaining();
    }

    @Override
    public void put(byte[] array) {
        this.buffer.put(array);
    }

    @Override
    public void get(byte[] array) {
        this.buffer.get(array);
    }

    @Override
    public void put(byte b) {
        this.buffer.put(b);
    }

    @Override
    public byte get() {
        return this.buffer.get();
    }

    @Override
    public void putInt(int i) {
        this.buffer.putInt(i);
    }

    @Override
    public int getInt() {
        return this.buffer.getInt();
    }

    @Override
    public void putShort(short s) {
        this.buffer.putShort(s);
    }

    @Override
    public short getShort() {
        return this.buffer.getShort();
    }

    @Override
    public char getChar() {
        return this.buffer.getChar();
    }

    @Override
    public void putChar(char c) {
        this.buffer.putChar(c);
    }

    @Override
    public long getLong() {
        return this.buffer.getLong();
    }

    @Override
    public void putLong(long l) {
        this.buffer.putLong(l);
    }

    @Override
    public float getFloat() {
        return this.buffer.getFloat();
    }

    @Override
    public void putFloat(float f) {
        this.buffer.putFloat(f);
    }

    @Override
    public double getDouble() {
        return this.buffer.getDouble();
    }

    @Override
    public void putDouble(double d) {
        this.buffer.putDouble(d);
    }

    @Override
    public byte[] toByteArray() {
        return this.buffer.array();
    }

    @Override
    public void close() throws IOException {

    }

}
