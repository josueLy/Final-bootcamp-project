package com.bootcamp.accountService.accountService.service.impl;

import com.bootcamp.accountService.accountService.config.entity.Account;
import com.bootcamp.accountService.accountService.dto.AccountDto;
import com.bootcamp.accountService.accountService.dto.UserDto;
import com.bootcamp.accountService.accountService.producer.KafkaAccountProducer;
import com.bootcamp.accountService.accountService.repository.IAccountRepository;
import com.bootcamp.accountService.accountService.service.interfaces.IAccountService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    private KafkaAccountProducer kafkaAccountProducer;


    @Autowired
    AccountServiceImpl (KafkaAccountProducer kafkaAccountProducer) {
        this.kafkaAccountProducer = kafkaAccountProducer;
    }

    @Override
    public void save(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setType(accountDto.getType());
        account.setAvaliableBalanceBootCoin(accountDto.getAvaliableBalanceBootCoin());
        account.setAvaliableBalanceSoles(accountDto.getAvaliableBalanceSoles());

        Mono<Account> accountMono= accountRepository.save(account);

        accountMono.subscribe(bank_account->{
           UserDto userDto=  new UserDto();
           userDto.setUserId(accountDto.getUserId());
           String message =new Gson().toJson(userDto);
           this.kafkaAccountProducer.sendAccount(message);
        });

    }




}
