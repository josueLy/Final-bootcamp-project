package com.bootcamp.userService.userService.controller;

import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.producer.KafkaUserProducer;
import com.bootcamp.userService.userService.service.interfaces.IUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class KafkaUserController {

    private KafkaUserProducer kafkaUserProducer;

    @Autowired
    private IUserService userService;

    @Autowired
    KafkaUserController(KafkaUserProducer kafkaUserProducer){
        this.kafkaUserProducer= kafkaUserProducer;
    }
    @PostMapping(value = "/create")
    public void sendMessageToKafkaTopic (@RequestBody UserDto userDto) {

        userService.save(userDto);
        String message = new Gson().toJson(userDto);

        this.kafkaUserProducer.sendUser(message);
    }
}
