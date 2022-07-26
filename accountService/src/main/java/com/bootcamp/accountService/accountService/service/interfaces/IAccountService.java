package com.bootcamp.accountService.accountService.service.interfaces;

import com.bootcamp.accountService.accountService.dto.AccountDto;
import com.bootcamp.accountService.accountService.entity.Account;
import reactor.core.publisher.Mono;

public interface IAccountService {

    Mono<Account> save(AccountDto accountDto);


}
