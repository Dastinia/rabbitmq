package com.atguigu.rabbitmq.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dastinia
 * @version 1.0
 * @date 2022/7/28
 */
public class Consumer {
    // 队列的名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.88.128");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否自动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取录消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true, ((s, delivery) -> {
            System.out.println(new String(delivery.getBody()));
        }), (e -> {
            System.out.println("消费消息被中断");
        }));
    }
}
