package com.opencc.JAVANIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.sql.Connection;
import java.sql.Statement;

class  TestA{
    private final int a;

    public TestA() {
        this.a = 10;
    }

}

public class NIOTest {
    static{
        System.out.println("load NIOTest");
    }
    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
        new Thread(() -> System.out.println(a));
    }

    public static void dump(ReadableByteChannel src, WritableByteChannel dest) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try (ReadableByteChannel srcCH = src; WritableByteChannel destCH = dest) {
            while (srcCH.read(buffer) != -1) {
                buffer.flip();
                destCH.write(buffer);
                buffer.clear();
            }
        } finally {
            if (src instanceof AutoCloseable) {
                ((AutoCloseable) src).close();
            }
            if (dest instanceof AutoCloseable) {
                ((AutoCloseable) dest).close();
            }
        }
    }
}
