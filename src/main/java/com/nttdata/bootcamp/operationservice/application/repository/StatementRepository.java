package com.nttdata.bootcamp.operationservice.application.repository;

import com.nttdata.bootcamp.operationservice.domain.Statement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatementRepository {
    Flux<Statement> queryAll();
    Mono<Statement> findById(String id);
    Mono<Statement> create(Statement statement);
    Mono<Statement> update(String id, Statement statement);
    Mono<Void> delete(String id);
}
