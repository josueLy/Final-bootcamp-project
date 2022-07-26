package com.bootcamp.userService.userService.controller;

import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.User;
import com.bootcamp.userService.userService.producer.KafkaUserProducer;
import com.bootcamp.userService.userService.service.interfaces.IUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<User> sendMessageToKafkaTopic (@RequestBody UserDto userDto) {


        String message = new Gson().toJson(userDto);

        this.kafkaUserProducer.sendUser(message);
        //userService.save(userDto);
        return userService.save(userDto);
    }
    @PutMapping("/update")
    public Mono<User> update(@RequestBody UserDto userDto){

        return userService.update(userDto);
    }
    @GetMapping("/showBydni/{dni}")
    public Mono<User> showBydni(@PathVariable String dni){
        return userService.findByDni(dni);
    }
}
