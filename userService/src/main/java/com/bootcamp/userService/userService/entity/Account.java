package com.bootcamp.userService.userService.entity;

import org.springframework.data.annotation.Id;

public class Account {
    @Id
    private String accountId;
    private double avaliableBalanceSoles;
    private double avaliableBalanceBootCoin;
    private String type;
}
