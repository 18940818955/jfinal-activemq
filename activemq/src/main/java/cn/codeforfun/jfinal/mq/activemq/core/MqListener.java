package cn.codeforfun.jfinal.mq.activemq.core;

import javax.jms.Message;
import javax.jms.MessageListener;

public abstract class MqListener implements MessageListener {
    public abstract void onMessage(Message message);
}
