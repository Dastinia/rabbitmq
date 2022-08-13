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
public class Producer {
    // 队列名称
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 工厂IP 连接Rabbitmq的队列
        factory.setHost("192.168.88.128");
        // 用户名，密码
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 创建连接
        Connection connection = factory.newConnection();
        // 获取信道
        Channel channel = connection.createChannel();

        // 创建一个队列
        /**
         * 参数定义：
         * 1.队列名称
         * 2.队列里面的消息是否持久化
         * 3.该队列是否只供一个消费者进行消费
         * 4.是否自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false,null);

        //发消息
        String message = "测试消息";

        /**
         * 参数定义：
         * 1.发送到哪个交换机
         * 2.路由key值，本次是队列的名称
         * 3.其他参数
         * 4.发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
        channel.close();
        connection.close();
    }
}
