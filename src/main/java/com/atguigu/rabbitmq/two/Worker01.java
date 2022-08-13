package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作线程
 * @author Dastinia
 * @version 1.0
 * @date 2022/7/28
 */
public class Worker01 {
    // 队列名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        // 消息的接收
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                System.out.println("接收到的消息：" + new String(delivery.getBody()));
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
                System.out.println(s + "消息消费被中断");
            }
        };
        System.out.println("c2等待接收消息......");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
