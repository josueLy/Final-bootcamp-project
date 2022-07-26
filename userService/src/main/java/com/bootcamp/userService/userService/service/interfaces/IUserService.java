package com.bootcamp.userService.userService.service.interfaces;

import com.bootcamp.userService.userService.dto.AccountDto;
import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.User;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<User> save(UserDto userDto);



    Mono<User> updateUserAccount(AccountDto accountDto);

    Mono<User> findByDni(String dni);


}
