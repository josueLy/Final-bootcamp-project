package com.bootcamp.accountService.accountService.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaAccountProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaAccountProducer.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    public KafkaAccountProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAccount(String message)
    {
        LOGGER.info("Producing message {}",message);
        this.kafkaTemplate.send("send-topic-account",message);
    }


}
