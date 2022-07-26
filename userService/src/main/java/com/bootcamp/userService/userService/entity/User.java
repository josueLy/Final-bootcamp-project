package com.bootcamp.userService.userService.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String idUser;
    private String dni;
    private String phoneNumber;
    private String emailAddress;
    private List<Account> accounts;
}
