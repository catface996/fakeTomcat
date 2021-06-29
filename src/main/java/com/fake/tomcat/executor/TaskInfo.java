package com.fake.tomcat.executor;

import java.nio.channels.SocketChannel;

/**
 * @author by catface
 * @date 2021/6/28 1:57 下午
 */
public class TaskInfo {

    private Long taskId;

    private SocketChannel channel;

    private byte[] param;

    private byte[] result;

    public TaskInfo(SocketChannel channel, byte[] param) {
        this.channel = channel;
        this.param = param;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public void setChannel(SocketChannel channel) {
        this.channel = channel;
    }

    public byte[] getParam() {
        return param;
    }

    public void setParam(byte[] param) {
        this.param = param;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }
}
