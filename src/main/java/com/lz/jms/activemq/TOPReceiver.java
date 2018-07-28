package com.lz.jms.activemq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TOPReceiver {
	// 连接账号
	private static String userName = ActiveMQConnection.DEFAULT_USER;
	// 连接密码
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	// 连接地址
	private static String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) throws JMSException {
		// 连接到ActiveMQ服务器
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
		Connection connection = factory.createConnection();
		connection.start();
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建主题
		Topic topic = session.createTopic("topic-text");
		// 创建订阅
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			// 订阅接收方法
			public void onMessage(Message message) {
				TextMessage tm = (TextMessage) message;
				try {
					System.out.println("接收: " + tm.getText());
					//session.commit();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
