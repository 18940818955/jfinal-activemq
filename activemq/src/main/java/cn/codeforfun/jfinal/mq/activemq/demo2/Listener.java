package cn.codeforfun.jfinal.mq.activemq.demo2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by P0015475 on 2016/3/25.
 */
public class Listener implements MessageListener {
    public void onMessage(Message message) {
        try {
            System.out.println("用户一收到的消息为:" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
