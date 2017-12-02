package com.example.rabbitmqhello.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:Sender
 * Description:
 *
 * @Author Gyo
 * @Date 2017/11/8 11:44
 */
@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String context = "hello rabbitmq";
        System.out.println("sender: " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }

}
