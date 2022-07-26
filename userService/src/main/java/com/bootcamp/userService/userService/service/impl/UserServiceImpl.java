package com.bootcamp.userService.userService.service.impl;

import com.bootcamp.userService.userService.Repository.IUserRepository;
import com.bootcamp.userService.userService.dto.AccountDto;
import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.Account;
import com.bootcamp.userService.userService.entity.User;
import com.bootcamp.userService.userService.service.redis.RedisService;
import com.bootcamp.userService.userService.service.interfaces.IUserService;
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
        user.setIdUser(userDto.getIdUser());
        user.setDni(userDto.getDni());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmailAddress(userDto.getEmailAddress());

        redisService.saveUser(user.getIdUser(), user);

        return userRepository.save(user);
    }

    @Override
    @KafkaListener(topics = "send-topic-account", groupId = "group_id")
    public Mono<User> updateUserAccount(AccountDto accountDto) {
        Mono<User> userMono = userRepository.findById(accountDto.getUserId());
        userMono = userMono.map(user -> {
            User userObject = user;
            List<Account> accountList = userObject.getAccounts();

            Account account  =new Account(accountDto.getAccountId(), accountDto.getAvaliableBalanceSoles(),
                    accountDto.getAvaliableBalanceBootCoin(),accountDto.getType());

            accountList.add(account);
            userObject.setAccounts(accountList);
            return userObject;
        });
            userMono = userMono.flatMap(result ->{
                return userRepository.save(result);
            });

        return userMono;
    }

    public Mono<User> findByDni(String dni){
        return userRepository.findByDni(dni);
    }
}
