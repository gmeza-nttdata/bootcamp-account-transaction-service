package com.nttdata.bootcamp.accounttransactionservice.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationData {
    private String number;
    private BigDecimal amount;
}
