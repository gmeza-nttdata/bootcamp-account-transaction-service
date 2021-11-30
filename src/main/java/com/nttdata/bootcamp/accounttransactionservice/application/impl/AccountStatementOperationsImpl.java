package com.nttdata.bootcamp.accounttransactionservice.application.impl;

import com.nttdata.bootcamp.accounttransactionservice.application.AccountStatementOperations;
import com.nttdata.bootcamp.accounttransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountStatementOperationsImpl implements AccountStatementOperations {

    private final StatementRepository statementRepository;

    @Override
    public Flux<AccountStatement> queryAll() {
        return statementRepository.queryAll();
    }

    @Override
    public Mono<AccountStatement> findById(String id) {
        return statementRepository.findById(id);
    }

    @Override
    public Mono<AccountStatement> create(AccountStatement accountStatement) {
        return statementRepository.create(accountStatement);
    }

    @Override
    public Mono<AccountStatement> update(String id, AccountStatement accountStatement) {
        return statementRepository.update(id, accountStatement);
    }

    @Override
    public Mono<Void> delete(String id) {
        return statementRepository.delete(id);
    }
}
