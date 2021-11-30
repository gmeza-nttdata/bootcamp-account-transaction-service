package com.nttdata.bootcamp.accounttransactionservice.infrastructure.model.dao;

import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("Statement")
public class AccountStatementDao {

    private String id;
    private String productType;
    private String number;
    private OperationType operation;
    private BigDecimal amount;
    private LocalDateTime dateTime;
}
