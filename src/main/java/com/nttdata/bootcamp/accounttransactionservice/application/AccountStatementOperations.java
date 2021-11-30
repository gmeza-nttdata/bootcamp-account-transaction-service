package com.nttdata.bootcamp.accounttransactionservice.application;

import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountStatementOperations {

    Flux<AccountStatement> queryAll();
    Mono<AccountStatement> findById(String id);
    Mono<AccountStatement> create(AccountStatement accountStatement);
    Mono<AccountStatement> update(String id, AccountStatement accountStatement);
    Mono<Void> delete(String id);

}
