package cn.codeforfun.jfinalplugin.activemq.core;

import cn.codeforfun.jfinalplugin.activemq.exception.JFinalActiveMqException;
import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActiveMqPlugin implements IPlugin {

<<<<<<< HEAD
    public static Map<String, JFinalQueue> queueMap = new HashMap<>();
    public static Map<String, JFinalTopic> topicMap = new HashMap<>();
=======
    public static Map<String, JFinalQueue> queueMap = new HashMap<String, JFinalQueue>();

    private boolean isStarted = false;

    private static Log log = Log.getLog(ActiveMqPlugin.class);

    public void addQueue(JFinalQueue queue) {
        if (queueMap.containsKey(queue.getQueueName())) {
            try {
                throw new JFinalActiveMqException("There is a same name queue.The queue name is " + queue.getQueueName());
            } catch (JFinalActiveMqException e) {
                e.printStackTrace();
            }
        } else {
            queueMap.put(queue.getQueueName(), queue);
        }
    }

    public void addTopic(JFinalTopic topic) {
<<<<<<< HEAD
        if (topicMap.containsKey(topic.getTopicName())) {
            try {
                throw new JFinalActiveMqException("There is a same name topic.The topic name is " + topic.getTopicName());
            } catch (JFinalActiveMqException e) {
                e.printStackTrace();
            }
        } else {
            topicMap.put(topic.getTopicName(), topic);
        }
=======

>>>>>>> 368035f7854364ad66bd2f8ce3a7aad3c474cade
    }

    public boolean start() {
        if (isStarted) return true;
        //
<<<<<<< HEAD
        try {
            Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
            for (Map.Entry entry : entries) {
                JFinalQueue queue = (JFinalQueue) entry.getValue();
                if (queue.startQueue()) {
                    log.debug("The queue has been started.The name is " + queue.getQueueName());
                }
            }
            log.info("JFinal queue has been started.");
        } catch (Exception e) {
            log.info("JFinal queue start error.");
            e.printStackTrace();
        }
        try {
            Set<Map.Entry<String, JFinalTopic>> entries = topicMap.entrySet();
            for (Map.Entry entry : entries) {
                JFinalTopic topic = (JFinalTopic) entry.getValue();
                if (topic.startTopic()) {
                    log.debug("The topic has been started.The name is " + topic.getTopicName());
                }
            }
            log.info("JFinal topic has been started.");
        } catch (Exception e) {
            log.info("JFinal topic start error.");
            e.printStackTrace();
        }
=======
        Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
        for (Map.Entry entry : entries) {
            JFinalQueue queue = (JFinalQueue) entry.getValue();
            if (queue.startQueue()) {
                log.debug("The queue has been started.The name is " + queue.getQueueName());
            }
        }
        log.info("JFinal queue has been started");
>>>>>>> 368035f7854364ad66bd2f8ce3a7aad3c474cade
        isStarted = true;
        return true;
    }

    public boolean stop() {
        if (!isStarted) return true;
        //
<<<<<<< HEAD
        try {
            Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
            for (Map.Entry entry : entries) {
                JFinalQueue queue = (JFinalQueue) entry.getValue();
                if (queue.stopQueue()) {
                    log.debug("The queue has been stopped.The name is " + queue.getQueueName());
                }
            }
            log.info("JFinal queue has been stopped");
        } catch (Exception e) {
            log.info("JFinal queue stop error.");
            e.printStackTrace();
        }
        try {
            Set<Map.Entry<String, JFinalTopic>> entries = topicMap.entrySet();
            for (Map.Entry entry : entries) {
                JFinalTopic topic = (JFinalTopic) entry.getValue();
                if (topic.stopTopic()) {
                    log.debug("The topic has been stopped.The name is " + topic.getTopicName());
                }
            }
            log.info("JFinal topic has been stopped");
        } catch (Exception e) {
            log.info("JFinal topic stop error.");
            e.printStackTrace();
        }
=======
        Set<Map.Entry<String, JFinalQueue>> entries = queueMap.entrySet();
        for (Map.Entry entry : entries) {
            JFinalQueue queue = (JFinalQueue) entry.getValue();
            if (queue.stopQueue()) {
                log.debug("The queue has been stopped.The name is " + queue.getQueueName());
            }
        }
        log.info("JFinal queue has been stopped");
>>>>>>> 368035f7854364ad66bd2f8ce3a7aad3c474cade
        isStarted = false;
        return true;
    }
}
