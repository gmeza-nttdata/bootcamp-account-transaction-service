package com.nttdata.bootcamp.accounttransactionservice.application.impl;

import com.nttdata.bootcamp.accounttransactionservice.application.AccountOperations;
import com.nttdata.bootcamp.accounttransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.accounttransactionservice.application.service.AccountService;
import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationData;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationType;
import com.nttdata.bootcamp.accounttransactionservice.domain.entity.account.Account;
import com.nttdata.bootcamp.accounttransactionservice.domain.entity.account.AccountContract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountOperationsImpl implements AccountOperations {

    private final StatementRepository statementRepository;
    private final AccountService accountService;

    @Override
    public Mono<AccountStatement> deposit(OperationData operationData) {

        return accountService.get(operationData.getNumber())
                .flatMap(account ->
                        statementRepository.queryAll()
                            .filter(item -> item.getNumber().equals(account.getNumber()))
                            .filter(item -> this.filterDateRange(account.getContract(), item.getDateTime()))
                        .collectList()
                        .flatMap(statements -> {
                            BigDecimal fee = this.calculateFee(statements, account);
                            BigDecimal finalBalance = account.getBalance()
                                    .add(operationData.getAmount())
                                    .subtract(fee);

                            if (finalBalance.compareTo(BigDecimal.ZERO) < 0)
                                throw new IllegalArgumentException("Not enough balance to achieve transaction");

                            account.setBalance(finalBalance);
                            return accountService.update(account.getNumber(), account)
                                    .flatMap(updated -> statementRepository.create(
                                            new AccountStatement(updated,
                                                    OperationType.DEPOSIT,
                                                    operationData.getAmount(),
                                                    fee)
                                    ));
                        })
                );
    }

    @Override
    public Mono<AccountStatement> withdraw(OperationData operationData) {

        return accountService.get(operationData.getNumber())
                .flatMap(account ->
                        statementRepository.queryAll()
                                .filter(item -> item.getNumber().equals(account.getNumber()))
                                .filter(item -> this.filterDateRange(account.getContract(), item.getDateTime()))
                                .collectList()
                                .flatMap(statements -> {
                                    BigDecimal fee = this.calculateFee(statements, account);
                                    BigDecimal finalBalance = account.getBalance()
                                            .subtract(operationData.getAmount())
                                            .subtract(fee);

                                    if (finalBalance.compareTo(BigDecimal.ZERO) < 0)
                                        throw new IllegalArgumentException("Not enough balance to achieve transaction");

                                    account.setBalance(finalBalance);
                                    return accountService.update(account.getNumber(), account)
                                            .flatMap(updated -> statementRepository.create(
                                                    new AccountStatement(updated,
                                                            OperationType.WITHDRAWAL,
                                                            operationData.getAmount(),
                                                            fee)
                                            ));
                                }));
    }


    @Override
    public Flux<AccountStatement> getStatements(String accountNumber) {
        return statementRepository.queryAll()
                .filter(statement -> statement.getNumber().equals(accountNumber));
    }

    @Override
    public Flux<AccountStatement> getAllStatements() {
        return statementRepository.queryAll();
    }

    private BigDecimal calculateFee(List<AccountStatement> statements, Account account) {

        AccountContract c = account.getContract();
        Integer limit = (c.getHasDailyTransactionLimit())?
                c.getDailyTransactionLimit():c.getMonthlyTransactionLimit();

        if (limit > statements.size())
            return BigDecimal.ZERO;
        else
            return c.getTransactionFeeAfterLimit();

    }

    private boolean filterDateRange(AccountContract contract, LocalDateTime dt) {

        LocalDateTime lowerBound;
        LocalDateTime upperBound = LocalDateTime.now();

        if (contract.getHasDailyTransactionLimit()!=null &&
                contract.getHasDailyTransactionLimit()
        )
            lowerBound = LocalDateTime.of(upperBound.toLocalDate(),
                    LocalTime.of(0,0,0));
        else // MonthlyTransactionLimit
            lowerBound = LocalDateTime.of(LocalDate.of(upperBound.toLocalDate().getYear(),
                    upperBound.toLocalDate().getMonth(), 1), LocalTime.of(0,0,0));

        return dt.isAfter(lowerBound) && dt.isBefore(upperBound);
    }

}
