package com.fake.tomcat.executor;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author by catface
 * @date 2021/6/28 1:59 下午
 */
public class WorkerRunner implements Runnable{


    private TaskInfo taskInfo;

    private LinkedBlockingQueue<TaskInfo> finishedTaskQueue;

    public WorkerRunner(TaskInfo taskInfo,LinkedBlockingQueue<TaskInfo> finishedTaskQueue) {
        this.taskInfo = taskInfo;
        this.finishedTaskQueue = finishedTaskQueue;
    }

    @Override
    public void run() {
        // 处理
        System.out.println(taskInfo.toString());
        // 返回结果
        finishedTaskQueue.add(taskInfo);
    }
}
