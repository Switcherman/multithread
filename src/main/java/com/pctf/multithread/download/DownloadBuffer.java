package com.pctf.multithread.download;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DownloadBuffer implements Closeable {

    private long globalOffset;
    private long upperBound;
    private int offset = 0;
    private final Storage storage;
    private final ByteBuffer byteBuffer;

    public DownloadBuffer(long lowerBound, long upperBound, Storage storage) {
        this.globalOffset = lowerBound;
        this.upperBound = upperBound;
        this.storage = storage;
        this.byteBuffer = ByteBuffer.allocate(1024 * 1024);
    }

    @Override
    public void close() throws IOException {
        if(globalOffset < upperBound) {
            flush();
        }
    }

    public void write(ByteBuffer inBuffer) throws IOException{
        int length = inBuffer.position();
        int capacity = byteBuffer.capacity();
        if(length + offset > capacity) {
            flush();
        }
        byteBuffer.position(offset);
        inBuffer.flip();
        byteBuffer.put(inBuffer);
        offset += length;
    }

    public void flush() throws IOException{
        byteBuffer.flip();
        int length = storage.store(globalOffset, byteBuffer);
        offset = 0;
        byteBuffer.clear();
    }
}
