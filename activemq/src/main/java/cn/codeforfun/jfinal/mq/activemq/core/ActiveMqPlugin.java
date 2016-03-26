package cn.codeforfun.jfinal.mq.activemq.core;

import com.jfinal.plugin.IPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActiveMqPlugin implements IPlugin {

    private static JFinalQueue queue;


    private boolean isStarted = false;


    public void setQueue(JFinalQueue queue) {
        this.queue = queue;
    }

    public void addTopic(JFinalTopic topic) {

    }

    public boolean start() {
        if (isStarted) return true;
        //
        queue.startQueue();
        isStarted = true;
        return true;
    }

    public boolean stop() {
        if (!isStarted) return true;
        //
        queue.stopQueue();
        isStarted = false;
        return true;
    }
}
