package com.pctf.multithread.download;

import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

public class BigFileDownloader {

    private final URL url;
    private final long fileSize;
    private final AtomicBoolean taskCanceled = new AtomicBoolean(false);
    private final Storage storage;

    public BigFileDownloader(String urlStr) throws Exception{
        this.url = new URL(urlStr);
        this.fileSize = getRemoteFileSize(url);
        String fileName = urlStr.substring(urlStr.lastIndexOf("/") + 1);
        this.storage = new Storage(fileSize, fileName);
    }

    public void download(int taskCount, long reportInterval) throws Exception{
        long perSize = fileSize / taskCount;
        long lowerBound = 0;
        long upperBound = 0;
        for(int i = taskCount - 1; i >= 0; i++) {
            lowerBound = perSize * i;
            if(i == taskCount - 1) {
                upperBound = fileSize;
            } else {
                upperBound = lowerBound + perSize - 1;
            }
            DownloadTask dt = new DownloadTask(url, lowerBound, upperBound, storage, taskCanceled);
            dispatchTask(dt, i);
        }
        doClean();
        reportProcess();
    }

    private void reportProcess(){

    }

    private void doClean() {
    }

    private void dispatchTask(final DownloadTask dt, int i) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dt.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    cancelDownload();
                }
            }
        });
        t.setName("downloadTask" + i);
        t.start();
    }

    private void cancelDownload() {
        if(taskCanceled.compareAndSet(false, true)) {
            doClean();
        }
    }

    private long getRemoteFileSize(URL url) {
        return 0;
    }
}
