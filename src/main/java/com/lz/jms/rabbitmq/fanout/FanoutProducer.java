package com.lz.jms.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by dy on 16-4-28.
 */
public class FanoutProducer {
    public String host ="127.0.0.1";
    public Connection connection;
    public Channel channel;


    public void init() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        //获取链接
        connection = factory.newConnection();
        //创建信道
        channel = connection.createChannel();

        /**
         * 声明交换器
         * 交换器类型主要有三种
         * direct：精准匹配路由键,fanout：广播匹配,topic模糊(多)匹配路由键(可以优先级)
         * exchangeDeclare(String exchange, String type)
         */
        channel.exchangeDeclare(Constants.fanout_exchange, "fanout");


    }
    public void publish(String msg) throws Exception{
        //basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        this.channel.basicPublish(Constants.fanout_exchange,"", null, msg.getBytes("UTF-8"));

    }
    public void close() throws Exception{
        channel.close();
        connection.close();
    }

    public static void main(String[] args) {
        try {
            FanoutProducer producer = new FanoutProducer();
            producer.init();
            String msg ="我是来自 fanout producer的消息!我的序列是:";
            for (int i = 1;i<8;i++){
                producer.publish(msg+i);
            }
            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}