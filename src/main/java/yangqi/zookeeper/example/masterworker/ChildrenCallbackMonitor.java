/*
 * Copyright 1999-2010 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package yangqi.zookeeper.example.masterworker;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.*;
import org.apache.zookeeper.AsyncCallback.ChildrenCallback;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;

public class ChildrenCallbackMonitor {

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        final ZooKeeper zookeeper = new ZooKeeper("192.168.6.55:2181", 2000, null);

        final ChildrenCallback callback = new ChildrenCallback() {

            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children) {
                System.out.println("aaa,children" + children);//sleep 长就调用

            }

        };

        Watcher watcher = new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("Event is " + event);
                if (event.getType() == EventType.NodeChildrenChanged) {
                    System.out.println("aaa Changed " + event);
                    zookeeper.getChildren("/workers", this, callback, null);
                }
            }
        };
//        zookeeper.create("/workers/children", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.PERSISTENT);
//        zookeeper.getData("/workers", watcher, new Stat());
        zookeeper.getChildren("/workers", watcher, callback, null);

        System.out.println("aaa begin finish");//调用
        Thread.sleep(200000);
        System.out.println("aaa finish");//调用

    }

}
