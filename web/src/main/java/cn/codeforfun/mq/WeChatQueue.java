package cn.codeforfun.mq;

import cn.codeforfun.jfinal.mq.activemq.core.JFinalQueue;

/**
 * Created by Administrator on 2016/3/26.
 */
public class WeChatQueue extends JFinalQueue {
    public WeChatQueue(String queueName) {
        super(queueName);
    }
}
