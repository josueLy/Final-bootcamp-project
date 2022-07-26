package com.bootcamp.userService.userService.Repository;

import com.bootcamp.userService.userService.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IUserRepository extends ReactiveCrudRepository <User,String> {

    Mono<User> findByDni(String dni);
}
