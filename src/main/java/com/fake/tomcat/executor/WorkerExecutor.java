package com.fake.tomcat.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author by catface
 * @date 2021/6/28 1:48 下午
 */
public class WorkerExecutor {

    private ExecutorService executorService;

    private LinkedBlockingQueue<TaskInfo> finishedTaskQueue;

    public WorkerExecutor(int size) {
        finishedTaskQueue = new LinkedBlockingQueue<>();
        executorService = Executors.newFixedThreadPool(size);
    }

    public void execute(TaskInfo task) {
        executorService.execute(new WorkerRunner(task,finishedTaskQueue));
    }

}
