package com.pctf.multithread.download;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

public class Storage implements Closeable, AutoCloseable {
    private static final String filePath = "/users/Lucifer/Downloads";
    private final RandomAccessFile accessFile;
    private final AtomicLong totalWrites = new AtomicLong(0);
    private final FileChannel channel;

    public Storage(long fileSize, String fileName) throws Exception{
        String fullFileName = filePath + "/" + fileName;
        String localFileName = createLocalFile(fullFileName, fileSize);
        accessFile = new RandomAccessFile(localFileName, "rw");
        channel = accessFile.getChannel();
    }

    private String createLocalFile(String fullFileName, long fileSize) throws Exception{
        File file = new File(fullFileName);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        } finally {
            Tools.silentClose(raf);
        }
        return fullFileName;
    }

    @Override
    public synchronized void close() throws IOException {
        if(channel.isOpen()) {
            Tools.silentClose(channel, accessFile);
        }
    }

    public int store(long globalOffset, ByteBuffer byteBuffer) throws IOException{
        channel.write(byteBuffer, globalOffset);
        int length = byteBuffer.limit();
        totalWrites.addAndGet(length);
        return length;
    }
}
