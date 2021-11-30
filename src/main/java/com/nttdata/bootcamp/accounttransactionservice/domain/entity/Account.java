package com.nttdata.bootcamp.accounttransactionservice.domain.entity;

import com.nttdata.bootcamp.accounttransactionservice.domain.dto.AccountContract;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.AccountType;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.Type;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class Account {

    private String number;
    private Integer userId;
    private AccountType type;
    private String currencyName;
    private BigDecimal balance;
    private AccountContract contract;

    // Only for Business:
    private List<Integer> holders;
    private List<Integer> signers;

}
