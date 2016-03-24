package cn.codeforfun.jfinal.mq.activemq.core;

import com.jfinal.log.Log;
import com.jfinal.plugin.IPlugin;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActiveMqPlugin implements IPlugin {

    private Log log = Log.getLog(ActiveMqPlugin.class);

    private ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接
    private Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接
    private Session session;    // Session： 一个发送或接收消息的线程
    private Destination destination;// Destination ：消息的目的地;消息发送给谁.
    private MessageConsumer consumer;// 消费者，消息接收者

    private String TCPURL = "tcp://localhost:61616";

    public ActiveMqPlugin() {
        try {
            connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, TCPURL);
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive();
                if (null != message) {
                    log.info("收到消息:" + message.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public boolean start() {
        return false;
    }

    public boolean stop() {
        return false;
    }
}
