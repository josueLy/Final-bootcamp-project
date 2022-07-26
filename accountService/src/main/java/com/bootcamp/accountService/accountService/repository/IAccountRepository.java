package com.bootcamp.accountService.accountService.repository;

import com.bootcamp.accountService.accountService.config.entity.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IAccountRepository extends ReactiveCrudRepository<Account,String> {
}
