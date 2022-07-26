package com.bootcamp.accountService.accountService.service.impl;

import com.bootcamp.accountService.accountService.dto.AccountDto;
import com.bootcamp.accountService.accountService.entity.Account;
import com.bootcamp.accountService.accountService.repository.IAccountRepository;
import com.bootcamp.accountService.accountService.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Mono<Account> save(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setType(accountDto.getType());
        account.setAvaliableBalanceBootCoin(accountDto.getAvaliableBalanceBootCoin());
        account.setAvaliableBalanceSoles(accountDto.getAvaliableBalanceSoles());

        return accountRepository.save(account);
    }




}
