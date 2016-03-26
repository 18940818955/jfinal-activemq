package cn.codeforfun.jfinal.mq.activemq.core;

import com.jfinal.plugin.IPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActiveMqPlugin implements IPlugin {

    private static Map<String, JFinalQueue> queueMap = new HashMap<String, JFinalQueue>();

    private boolean isStarted = false;


    public void addQueue(JFinalQueue queue) {
        if (queueMap.containsKey(queue.getQueueName())) {
            try {
                throw new JFinalActiveMqException("there is a same name queue");
            } catch (JFinalActiveMqException e) {
                e.printStackTrace();
            }
        } else {
            queueMap.put(queue.getQueueName(), queue);
        }
    }

    public void addTopic(JFinalTopic topic) {

    }

    public boolean start() {
        if (isStarted) return true;
        //
        Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
        for (Map.Entry entry : entries) {
            JFinalQueue queue = (JFinalQueue) entry.getValue();
            queue.startQueue();
        }
        isStarted = true;
        return true;
    }

    public boolean stop() {
        if (!isStarted) return true;
        //
        Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
        for (Map.Entry entry : entries) {
            JFinalQueue queue = (JFinalQueue) entry.getValue();
            queue.stopQueue();
        }
        isStarted = false;
        return true;
    }
}
