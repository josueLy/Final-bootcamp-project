package com.bootcamp.userService.userService.dto;

import com.bootcamp.userService.userService.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String idUser;
    private String dni;
    private String phoneNumber;
    private String emailAddress;
    private List<Account> accounts;

}
