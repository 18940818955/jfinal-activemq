package cn.codeforfun.jfinalplugin.activemq.core;

import com.jfinal.log.Log;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public abstract class JFinalQueue implements Runnable, MessageListener {

    private static Log log = Log.getLog(JFinalQueue.class);

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String brokerUrl = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String queueName = null;
    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private static Session session;
    private static Destination destination;
    private static MessageProducer producer;
    private MessageConsumer consumer;
    volatile boolean isStarted = false;

    public JFinalQueue(String queueName) {
        this.queueName = queueName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBrokerUrl(String url) {
        this.brokerUrl = url;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public static void sendMessage(String message) {
        if (message == null) {
            log.info("Message is null.");
            return;
        }
        try {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            producer = session.createProducer(destination);
            producer.send(textMessage);
        } catch (JMSException e) {
            log.error("Send message error.");
            e.printStackTrace();
        }
    }

    public void run() {
        if (queueName == null) {
            log.error("Queue name can't be null.");
            throw new NullPointerException();
        }
        try {
            connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    ActiveMQConnection.DEFAULT_BROKER_URL);
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue(queueName);
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public boolean startQueue() {
        if (isStarted) return true;
        Thread thread = new Thread(this);
        thread.start();
        isStarted = true;
        log.info("JFinal queue is started.");
        return true;
    }

    public boolean stopQueue() {
        if (!isStarted) return true;
        try {
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        isStarted = false;
        log.info("JFinal queue is stopped.");
        return true;
    }

    public abstract void onMessage(Message message);
}
