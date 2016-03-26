package cn.codeforfun.mq;

import cn.codeforfun.jfinal.mq.activemq.core.JFinalQueue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2016/3/26.
 */
public class WeChatQueue extends JFinalQueue {

    public WeChatQueue(String queueName) {
        super(queueName);
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("收到的消息为:" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
