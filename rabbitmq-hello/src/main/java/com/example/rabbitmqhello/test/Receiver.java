package com.example.rabbitmqhello.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * ClassName:Receiver
 * Description:
 *
 * @Author Gyo
 * @Date 2017/11/8 11:53
 */
@Component
public class Receiver {

    @RabbitHandler
    @RabbitListener(queues = "hello")
    public void process(String hello){
        System.out.println("Receiver :" + hello);
    }

}
