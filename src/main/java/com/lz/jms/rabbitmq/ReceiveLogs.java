package com.lz.jms.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogs {

	private static final String EXCHANGE_NAME = "lzw";
	private static final String LZW_MQ_TEST_QUEUE = "LZW_MQ_TEST_QUEUE";

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		channel.queueBind(LZW_MQ_TEST_QUEUE, EXCHANGE_NAME, "");

		System.out.println(" 等待消息， 退出请按： CTRL+C");

		//订阅消息
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(LZW_MQ_TEST_QUEUE, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());

			System.out.println(" 接收到消息：" + message);
		}
	}
}