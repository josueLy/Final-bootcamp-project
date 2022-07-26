package com.bootcamp.userService.userService.service.redis.redis;

import com.bootcamp.userService.userService.dto.UserDto;
import com.bootcamp.userService.userService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private ReactiveRedisOperations<String, User> userOps;

    public void saveUser(String key, User user){
        ReactiveValueOperations<String,User> ops = userOps.opsForValue();
        ops.set(key,user).subscribe();
    }
}
