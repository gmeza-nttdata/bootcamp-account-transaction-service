package com.nttdata.bootcamp.accounttransactionservice.domain.entity.account;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Account {

    /** account number or id. */
    private String number;
    /** main holder. */
    private Integer userId;
    /** account type. */
    private String type;
    /** Currency of the account. */
    private String currencyName;
    /** Current Account balance. */
    private BigDecimal balance;
    /** Account Contract. */
    private AccountContract contract;

    // Only for Business:
    /** Holders list. */
    private List<Integer> holders;
    /** Singers list. */
    private List<Integer> signers;

}
