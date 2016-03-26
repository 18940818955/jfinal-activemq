package cn.codeforfun.jfinalplugin.activemq.core;

import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActiveMqPlugin implements IPlugin {

    private static Map<String, JFinalQueue> queueMap = new HashMap<String, JFinalQueue>();

    private boolean isStarted = false;

    private static Log log = Log.getLog(ActiveMqPlugin.class);

    public void addQueue(JFinalQueue queue) {
        if (queueMap.containsKey(queue.getQueueName())) {
            try {
                throw new JFinalActiveMqException("There is a same name queue");
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
            log.info(queue.getQueueName() + " queue has been started");
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
            log.info(queue.getQueueName() + " queue has been stopped");
        }
        isStarted = false;
        return true;
    }
}
