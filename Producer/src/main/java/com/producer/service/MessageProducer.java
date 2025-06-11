package com.producer.service;

import com.producer.confing.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message)
    {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME,message);
    }
}
