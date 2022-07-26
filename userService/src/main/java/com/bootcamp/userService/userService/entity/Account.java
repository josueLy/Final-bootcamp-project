package com.bootcamp.userService.userService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Account {
    @Id
    private String accountId;
    private double avaliableBalanceSoles;
    private double avaliableBalanceBootCoin;
    private String type;
}
