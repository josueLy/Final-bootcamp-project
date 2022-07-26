package com.bootcamp.userService.userService.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaUserProducer.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    public KafkaUserProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String,String> kafkaTemplate ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUser(String message){
        LOGGER.info("Producing message {}",message);
        this.kafkaTemplate.send("bootcamp-final",message);

    }
}
