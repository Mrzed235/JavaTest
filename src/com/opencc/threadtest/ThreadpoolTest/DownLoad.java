package com.opencc.threadtest.ThreadpoolTest;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownLoad {
    public static void main(String[] args) throws Exception {
        URL[] urls = {
                new URL("http://openhome.cc/Gossip/Encoding"),
                new URL("https://openhome.cc/Gossip/Encoding/URLEncoding.html"),
                new URL("http://openhome.cc/Gossip/Scala"),
                new URL("http://openhome.cc/Gossip/JavaScript"),
                new URL("http://openhome.cc/Gossip/Python")
        };
        String[] fileNames = {
                "D:\\workbenchDemo\\gopath\\src\\JavaTest\\src\\com\\opencc\\threadtest\\ThreadpoolTest\\files\\Encoding.html",
                "D:\\workbenchDemo\\gopath\\src\\JavaTest\\src\\com\\opencc\\threadtest\\ThreadpoolTest\\files\\URLEncoding.txt",
                "D:\\workbenchDemo\\gopath\\src\\JavaTest\\src\\com\\opencc\\threadtest\\ThreadpoolTest\\files\\Scala.html",
                "D:\\workbenchDemo\\gopath\\src\\JavaTest\\src\\com\\opencc\\threadtest\\ThreadpoolTest\\files\\JavaScript.html",
                "D:\\workbenchDemo\\gopath\\src\\JavaTest\\src\\com\\opencc\\threadtest\\ThreadpoolTest\\files\\Python.html"
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        new Pages(urls, fileNames, executorService).download();
        executorService.shutdown();
    }
}
