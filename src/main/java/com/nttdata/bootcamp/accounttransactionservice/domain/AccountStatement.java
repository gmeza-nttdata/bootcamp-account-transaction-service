package com.nttdata.bootcamp.accounttransactionservice.domain;

import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationType;
import com.nttdata.bootcamp.accounttransactionservice.domain.entity.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountStatement {
    private String id;
    private String productType;
    private String number;
    private OperationType operation;
    private BigDecimal amount;
    private LocalDateTime dateTime;

    public AccountStatement(){}

    public AccountStatement(Account account, OperationType operation, BigDecimal amount) {
        this.productType = account.getType().toString();
        this.number = account.getNumber();
        this.dateTime = LocalDateTime.now();
        this.operation = operation;
        this.amount = amount;
    }


}
