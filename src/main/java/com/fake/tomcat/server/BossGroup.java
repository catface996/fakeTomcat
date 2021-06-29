package com.fake.tomcat.server;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author by catface
 * @date 2021/6/25 11:38 上午
 */
public class BossGroup {

    private ServerSocketChannel server;
    private WorkGroup workGroup;

    public BossGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            for (; ; ) {
                SocketChannel channel = server.accept();
                if (channel != null) {
                    channel.configureBlocking(false);
                    workGroup.register(channel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
