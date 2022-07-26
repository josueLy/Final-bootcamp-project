package com.bootcamp.userService.userService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {

    private String accountId;
    private double avaliableBalanceSoles;
    private double avaliableBalanceBootCoin;
    private String type;
    private String userId;
}
