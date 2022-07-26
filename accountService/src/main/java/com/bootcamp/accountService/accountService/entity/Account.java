package com.bootcamp.accountService.accountService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Account")
public class Account {
    @Id
    private String accountId;
    private double avaliableBalanceSoles;
    private double avaliableBalanceBootCoin;
    private String type;

}
