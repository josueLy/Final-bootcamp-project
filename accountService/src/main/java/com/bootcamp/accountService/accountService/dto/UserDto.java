package com.bootcamp.accountService.accountService.dto;

import com.bootcamp.accountService.accountService.config.entity.Account;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userId;
    private List<Account> accounts;
}
