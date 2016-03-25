package cn.codeforfun.mq;

import cn.codeforfun.jfinal.mq.activemq.core.MqListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2016/3/25.
 */
public class WeChatListener extends MqListener {
    public void onMessage(Message message) {
        try {
            System.out.println("收到的消息为" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
