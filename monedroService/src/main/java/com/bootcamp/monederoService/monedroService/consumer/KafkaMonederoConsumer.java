package com.bootcamp.monederoService.monedroService.consumer;

import com.bootcamp.monederoService.monedroService.producer.KafkaMonederoProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaMonederoConsumer {

    Logger logger = LoggerFactory.getLogger(KafkaMonederoConsumer.class);

    @KafkaListener(topics = "bootcamp-proyecto4", groupId = "group_id")
    public void consume(String message){
        logger.info("Consuming message {}",message);
    }

}
