package com.pctf.multithread.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadTask implements Runnable {

    private final long lowerBound;
    private final long upperBound;
    private final Storage storage;
    private final DownloadBuffer downloadBuffer;
    private final AtomicBoolean cancelFlag;
    private final URL url;

    public DownloadTask(URL url, long lowerBound, long upperBound, Storage storage, AtomicBoolean taskCanceled) {
        this.url = url;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.storage = storage;
        this.cancelFlag = taskCanceled;
        this.downloadBuffer = new DownloadBuffer(lowerBound, upperBound, storage);
    }

    private InputStream issueRequest(URL url, long lowerBound, long upperBound) throws IOException {
        return null;
    }

    @Override
    public void run() {
        if(cancelFlag.get()) {
            return;
        }
        ReadableByteChannel channel;
        try {
            channel = Channels.newChannel(issueRequest(url, lowerBound, upperBound));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while(!cancelFlag.get() && channel.read(byteBuffer) > 0) {
                downloadBuffer.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Tools.silentClose(storage, downloadBuffer);
        }
    }
}
