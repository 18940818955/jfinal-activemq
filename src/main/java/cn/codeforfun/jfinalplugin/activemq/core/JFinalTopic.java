package cn.codeforfun.jfinalplugin.activemq.core;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class JFinalTopic implements Runnable {
    private static Log log = Log.getLog(JFinalTopic.class);

    private Map<String, MessageListener> listenerHashMap = new HashMap<>();
    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String brokerUrl = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String topicName = null;
    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private boolean isStarted = false;

    public JFinalTopic(String topicName) {
        this.topicName = topicName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void addListener(MessageListener messageListener) {
        if (listenerHashMap.containsKey(messageListener.getClass().getName())) {
            log.error("The topic listener is exist.Class name is " + messageListener.getClass().getName());
            return;
        }
        listenerHashMap.put(messageListener.getClass().getName(), messageListener);
    }

    public static void sendMessage(String topicName, String message) {
        if (StrKit.isBlank(message)) {
            log.debug("Message is blank.");
            return;
        }
        if (!ActiveMqPlugin.topicMap.containsKey(topicName)) {
            log.error("The topic is not exist.The topic name is " + topicName);
            return;
        }
        JFinalTopic topic = ActiveMqPlugin.topicMap.get(topicName);
        try {
            TextMessage textMessage = topic.session.createTextMessage();
            textMessage.setText(message);
            topic.producer = topic.session.createProducer(topic.destination);
            topic.producer.send(textMessage);
        } catch (JMSException e) {
            log.error("Send message error.");
            e.printStackTrace();
        }
    }

    public void run() {
        if (topicName == null) {
            log.error("Topic name can't be null.");
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
            destination = session.createTopic(topicName);
            Set<Map.Entry<String, MessageListener>> entries = listenerHashMap.entrySet();
            for (Map.Entry entry : entries) {
                MessageConsumer consumer = session.createConsumer(destination);
                consumer.setMessageListener((MessageListener) entry.getValue());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public boolean startTopic() {
        if (isStarted) return true;
        Thread thread = new Thread(this);
        thread.start();
        isStarted = true;
        return true;
    }

    public boolean stopTopic() {
        if (!isStarted) return true;
        try {
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        isStarted = false;
        return true;
    }
}
