package com.pctf.multithread.executor;

import com.pctf.multithread.download.Tools;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

/**
 * 支持异步的xml parser
 */
public class XMLDocumentParser {

    public static ParsingTask createParsingTask(InputStream in) {
        return new ParsingTask(in, null, null);
    }

    public static ParsingTask createParsingTask(String urlStr, int connectTimeout, int readTimeout) throws IOException {
        URL url = new URL(urlStr);
        return new ParsingTask(url, connectTimeout, readTimeout);
    }

    // 封装结果处理
    public static abstract class ResultHandler {
        abstract void onSuccess(Document document) ;
        void onError(Throwable t) {
            t.printStackTrace();
        }
    }

    public static class ParsingTask {

        private final InputStream in;
        private volatile Executor executor;
        private volatile ResultHandler resultHandler;

        public ParsingTask(InputStream in, Executor executor, ResultHandler resultHandler) {
            this.in = in;
            this.executor = executor;
            this.resultHandler = resultHandler;
        }

        public ParsingTask(URL url, int connectTimeout, int readTimeout) throws IOException {
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            this.in = connection.getInputStream();
        }

        public Future<Document> execute() throws Exception {
            Callable<Document> callable = new Callable<Document>() {
                @Override
                public Document call() throws Exception {
                    return doParse(in);
                }
            };
            FutureTask<Document> future;
            if(executor != null) {
                future = new FutureTask<Document>(callable) {
                    @Override
                    public void done() {
                        callbackResultHandler(this, resultHandler);
                    }
                };
            } else {
                future = new FutureTask<>(callable);
            }
            if(executor != null) {
                executor.execute(future);
            } else {
                future.run();
            }
            return future;
        }

        void callbackResultHandler(FutureTask<Document> futureTask, ResultHandler rh) {
            if(futureTask.isCancelled()) {
                System.out.println("任务被取消");
                return;
            }
            try {
                rh.onSuccess(futureTask.get());
            } catch (Exception e) {
                rh.onError(e);
            }
        }

        public Document doParse(InputStream in) throws Exception{
            Document document = null;
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                document = db.parse(in);
            } finally {
                Tools.silentClose(in);
            }
            return document;
        }

        public Executor getExecutor() {
            return executor;
        }

        public void setExecutor(Executor executor) {
            this.executor = executor;
        }

        public ResultHandler getResultHandler() {
            return resultHandler;
        }

        public void setResultHandler(ResultHandler resultHandler) {
            this.resultHandler = resultHandler;
        }
    }
}
