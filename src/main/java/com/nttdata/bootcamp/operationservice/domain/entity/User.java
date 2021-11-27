package com.nttdata.bootcamp.operationservice.domain.entity;

import com.nttdata.bootcamp.operationservice.domain.dto.Type;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

    private Integer id;
    private Type type;
    private String fullName;
    private String address;
    private LocalDate birthDate;

}
