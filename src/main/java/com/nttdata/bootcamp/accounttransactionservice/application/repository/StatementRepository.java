package com.nttdata.bootcamp.accounttransactionservice.application.repository;

import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatementRepository {
    Flux<AccountStatement> queryAll();
    Mono<AccountStatement> findById(String id);
    Mono<AccountStatement> create(AccountStatement accountStatement);
    Mono<AccountStatement> update(String id, AccountStatement accountStatement);
    Mono<Void> delete(String id);
}
