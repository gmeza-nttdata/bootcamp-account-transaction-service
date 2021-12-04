package com.nttdata.bootcamp.accounttransactionservice.domain;

import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationType;
import com.nttdata.bootcamp.accounttransactionservice.domain.entity.account.Account;
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
    private BigDecimal fee;

    public AccountStatement(){}

    public AccountStatement(String number) {
        this.number = number;
    }

    public AccountStatement(Account account, OperationType operation, BigDecimal amount, BigDecimal fee) {
        this.productType = account.getType();
        this.number = account.getNumber();
        this.dateTime = LocalDateTime.now();
        this.operation = operation;
        this.amount = amount;
        this.fee = fee;
    }


}
