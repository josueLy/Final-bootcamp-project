package com.bootcamp.userService.userService.service.interfaces;

import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.User;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<User> save(UserDto userDto);

    Mono<User> update(UserDto userDto);


}
