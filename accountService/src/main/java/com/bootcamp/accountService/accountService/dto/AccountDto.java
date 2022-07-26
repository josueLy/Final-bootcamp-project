package com.bootcamp.accountService.accountService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
public class AccountDto {

    private String accountId;
    private double avaliableBalanceSoles;
    private double avaliableBalanceBootCoin;
    private String type;
    private String userId;
}
