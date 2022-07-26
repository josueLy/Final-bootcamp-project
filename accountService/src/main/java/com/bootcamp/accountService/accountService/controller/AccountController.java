package com.bootcamp.accountService.accountService.controller;

import com.bootcamp.accountService.accountService.dto.AccountDto;
import com.bootcamp.accountService.accountService.producer.KafkaAccountProducer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

        private  KafkaAccountProducer kafkaAccountProducer;
        @Autowired
        AccountController (KafkaAccountProducer kafkaAccountProducer) {
            this.kafkaAccountProducer = kafkaAccountProducer;
    }

        @PostMapping(value = "/create")
        public void sendMessageToKafkaTopic (@RequestBody AccountDto accountDto) {
            String message = new Gson().toJson(accountDto);
            this.kafkaAccountProducer.sendToUpdateAccount(message);
    }
}
