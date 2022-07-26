package com.bootcamp.userService.userService.service.impl;

import com.bootcamp.userService.userService.Repository.IUserRepository;
import com.bootcamp.userService.userService.dto.AccountDto;
import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.Account;
import com.bootcamp.userService.userService.entity.User;
import com.bootcamp.userService.userService.service.redis.RedisService;
import com.bootcamp.userService.userService.service.interfaces.IUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private RedisService redisService;

    @Override
    public Mono<User> save(UserDto userDto) {
        User user = new User();
        user.setDni(userDto.getDni());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmailAddress(userDto.getEmailAddress());

        return userRepository.save(user)
                .flatMap(userObject-> {
                    redisService.saveUser(user.getIdUser(), userObject);
                    return Mono.just(userObject);
                });
    }

    @Override
    @KafkaListener(topics = "send-topic-account", groupId = "group_id")
    public Mono<User> updateUserAccount(String message) {

        AccountDto accountDto = new  Gson().fromJson(message,AccountDto.class);

        Mono<User> userMono = userRepository.findById(accountDto.getUserId());
        userMono = userMono.flatMap(user ->
            this.addAccount(user,accountDto)
        ).flatMap(result ->{
                return userRepository.save(result);
            });

        return userMono;
    }

    private Mono<User> addAccount(User user,AccountDto accountDto)
    {
        List<Account> accountList = user.getAccounts();

        Account account  =new Account(accountDto.getAccountId(), accountDto.getAvaliableBalanceSoles(),
                accountDto.getAvaliableBalanceBootCoin(),accountDto.getType());

        accountList.add(account);
        user.setAccounts(accountList);
        return Mono.just(user);
    }

    public Mono<User> findByDni(String dni){
        return userRepository.findByDni(dni);
    }
}
