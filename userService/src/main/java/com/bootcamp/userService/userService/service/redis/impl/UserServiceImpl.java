package com.bootcamp.userService.userService.service.redis.impl;

import com.bootcamp.userService.userService.Repository.IUserRepository;
import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.User;
import com.bootcamp.userService.userService.service.redis.redis.RedisService;
import com.bootcamp.userService.userService.service.redis.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private RedisService redisService;

    @Override
    public Mono<User> save(UserDto userDto) {
        User user = new User();
        user.setIdUser(userDto.getIdUser());
        user.setDni(userDto.getDni());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmailAddress(userDto.getEmailAddress());

        redisService.saveUser(user.getIdUser(), user);

        return userRepository.save(user);
    }

    @Override
    public Mono<User> update(UserDto userDto) {
        Mono<User> userMono = userRepository.findById(userDto.getIdUser());
        userMono = userMono.map(user -> {
            User user1 = user;
            user1.setDni(userDto.getDni());
            user1.setPhoneNumber(userDto.getPhoneNumber());
            user1.setEmailAddress(userDto.getEmailAddress());
            user1.setAccounts(userDto.getAccounts());
            return user1;
        });
            userMono = userMono.flatMap(result ->{
                return userRepository.save(result);
            });

        return userMono;
    }
}
