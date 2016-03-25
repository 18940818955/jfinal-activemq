package cn.codeforfun.jfinal.mq.activemq.core;

import com.jfinal.plugin.IPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActiveMqPlugin implements IPlugin {

    private boolean isStarted = false;

    private Map<String, JFinalQueue> queueMap = new HashMap<String, JFinalQueue>();

    public void addQueue(JFinalQueue queue) {
        queueMap.put(queue.getQueueName(), queue);
    }

    public boolean start() {
        if (isStarted) return true;
        Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
        for (Map.Entry entry : entries) {
            JFinalQueueImpl queue = (JFinalQueueImpl) entry.getValue();
            String queueName = (String) entry.getKey();
            Thread thread = new Thread(queue);
            thread.setName(queueName);
            thread.getId();
            thread.start();
        }
        isStarted = true;
        return true;
    }

    public boolean stop() {
        if (!isStarted) return true;
        Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
        for (Map.Entry entry : entries) {
            JFinalQueueImpl queue = (JFinalQueueImpl) entry.getValue();
            queue.stop();
            String queueName = (String) entry.getKey();
            Thread thread = Thread.;
            thread.setName(queueName);
            thread.stop();
        }
        isStarted = false;
        return true;
    }
}
