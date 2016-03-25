package cn.codeforfun.jfinal.mq.activemq.demo2;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.leveldb.util.ProcessSupport;

import javax.jms.*;

/**
 * Created by P0015475 on 2016/3/25.
 */
public class Sender {
    public static void main(String[] args) {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                ActiveMQConnection.DEFAULT_BROKER_URL);
        // 构造从工厂得到连接对象
        try {
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createTopic("FirstQueue");
            producer = session.createProducer(destination);
            sendMessage(session, producer);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
        for (int i = 0; i < 5; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("消息" + (i + 1));
            System.out.println("发送的消息为:" + textMessage.getText());
            producer.send(textMessage);
        }
    }
}
