package com.opencc.threadtest.ThreadpoolTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Executor;

public class Pages {
    private URL[] urls;
    private String[] filenames;
    private Executor executor;

    public Pages(URL[] urls, String[] filenames, Executor executor) {
        this.urls = urls;
        this.filenames = filenames;
        this.executor = executor;
    }

    public void download() {
        for (int i = 0; i < urls.length; i++) {
            URL url = urls[i];
            String filename = filenames[i];
            executor.execute(() -> {
                try {
                    URLConnection conn = url.openConnection();
                    InputStream input = conn.getInputStream();
                    dump(input, Files.newOutputStream(Paths.get(filename)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void dump(InputStream src, OutputStream dest) throws IOException {
        try (InputStream s = src; OutputStream d = dest) {
            byte[] data = new byte[1024];
            int length;
            while ((length = s.read(data)) != -1) {
                d.write(data, 0, length);
            }
        }
    }
}
