package com.fake.tomcat;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author by catface
 * @date 2021/6/25 11:02 上午
 */
public class SelectorGroupThread extends Thread {

    private final LinkedBlockingDeque<SocketChannel> socketNeedRegister;
    int socketNum = 0;
    private Selector selector;

    public SelectorGroupThread() {
        socketNeedRegister = new LinkedBlockingDeque<>();
        try {
            selector = Selector.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.start();
    }

    public void add(SocketChannel channel) {
        socketNeedRegister.addLast(channel);
        selector.wakeup();
    }

    @Override
    public void run() {
        super.run();
        for (; ; ) {
            try {
                socketNum = selector.select();
                if (socketNum > 0) {
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        handleRead(keys.next());
                        keys.remove();
                    }
                }
                while (!socketNeedRegister.isEmpty()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketNeedRegister.pollFirst().register(selector, SelectionKey.OP_READ, buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRead(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        SocketChannel channel = (SocketChannel)key.channel();
        try {
            StringBuilder sb = new StringBuilder();
            for (; ; ) {
                buffer.clear();
                int num = channel.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data, 0, buffer.limit());
                    sb.append(new String(data));
                } else if (num == 0) {
                    System.out.println(sb);
                    break;
                } else {
                    key.cancel();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
