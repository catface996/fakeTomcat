package com.fake.tomcat;

import com.fake.tomcat.executor.WorkerExecutor;
import com.fake.tomcat.server.BossGroup;
import com.fake.tomcat.server.WorkGroup;

/**
 * @author by catface
 * @date 2021/6/25 11:49 上午
 */
public class MainStarter {

    public static void main(String[] args) {

        WorkGroup workGroup = new WorkGroup(3);
        BossGroup bossGroup = new BossGroup(workGroup);

        WorkerExecutor workerExecutor = new WorkerExecutor(3);

        bossGroup.bind(9999);

    }
}
