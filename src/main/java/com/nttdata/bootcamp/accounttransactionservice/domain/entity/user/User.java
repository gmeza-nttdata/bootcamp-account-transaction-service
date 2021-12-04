package com.nttdata.bootcamp.accounttransactionservice.domain.entity.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private Integer id;
    private String type;
    private String fullName;
    private String address;
    private LocalDate birthDate;

}
