package com.nttdata.bootcamp.accounttransactionservice.application.impl;

import com.nttdata.bootcamp.accounttransactionservice.application.AccountOperations;
import com.nttdata.bootcamp.accounttransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationData;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationType;
import com.nttdata.bootcamp.accounttransactionservice.infrastructure.service.AccountWebService;
import com.nttdata.bootcamp.accounttransactionservice.infrastructure.service.UserWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountOperationsImpl implements AccountOperations {

    private final StatementRepository statementRepository;
    private final AccountWebService accountWebService;

    @Override
    public Mono<AccountStatement> deposit(OperationData operationData) {

        return accountWebService.get(operationData.getNumber())
                .flatMap(account -> {
                    // Deposit Operation
                    account.setBalance(account.getBalance().add(operationData.getAmount()));
                    return accountWebService.update(account.getNumber(), account)
                            .flatMap(
                                    updatedAccount ->
                                            statementRepository
                                                    .create(new AccountStatement(updatedAccount, OperationType.DEPOSIT, operationData.getAmount()))
                            )
                            .onErrorReturn(new AccountStatement());
                })
                .onErrorReturn(new AccountStatement());
    }

    @Override
    public Mono<AccountStatement> withdraw(OperationData operationData) {
        return accountWebService.get(operationData.getNumber())
                .flatMap(account -> {
                    // Withdraw Operation

                    if (account.getBalance().compareTo(operationData.getAmount()) < 0)
                        return null;

                    account.setBalance(account.getBalance().subtract(operationData.getAmount()));
                    return accountWebService.update(account.getNumber(), account)
                            .flatMap(
                                    updatedAccount ->
                                            statementRepository
                                                    .create(new AccountStatement(account, OperationType.WITHDRAWAL, operationData.getAmount()))
                            )
                            .onErrorReturn(new AccountStatement());
                })
                .onErrorReturn(new AccountStatement());
    }


    @Override
    public Flux<AccountStatement> getStatements(String accountNumber) {
        return statementRepository.queryAll()
                .filter(statement -> statement.getNumber().equals(accountNumber));
    }
}
