package yangqi.code;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException
    {
        ZooKeeper zk = new ZooKeeper("192.168.6.55:2181", 3000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("aaa"+event);

            }

        });

        Stat e=zk.exists("/yangqi_test",null);

        System.out.println("aaa exists "+e);
        String create;
        if (e==null) {
            create = zk.create("/yangqi_test", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            System.out.println("aaa create:" + create);
        }
        zk.setData("/yangqi_test", "Data of node 3".getBytes(), -1);//get 命令能拿到这个值

        e=zk.exists("/yangqi_test",null);
    }

}
