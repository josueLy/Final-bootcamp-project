package com.bootcamp.monederoService.monedroService.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class KafkaMonederoProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMonederoProducer.class);

    private final KafkaTemplate<String,String> kafkaTemplate;


    public KafkaMonederoProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessageC(String message){
        LOGGER.info("Producing message {}",message);
        this.kafkaTemplate.send("bootcamp-final",message);

    }

    public void sendMenssageToUpdateAccount(String message)
    {
        LOGGER.info("Producing message {}",message);
        this.kafkaTemplate.send("recieve-send-topic",message);
    }

}
