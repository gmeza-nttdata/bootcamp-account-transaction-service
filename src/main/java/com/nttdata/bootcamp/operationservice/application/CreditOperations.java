package com.nttdata.bootcamp.operationservice.application;

/*
 * Un cliente puede hacer pagos de sus productos de crédito.
 *
 * Un cliente puede cargar consumos a sus tarjetas de crédito en base a su límite de crédito.
 *
 * El sistema debe permitir consultar los saldos disponibles de tarjetas de crédito.
 *
 * El sistema debe permitir consultar todos los movimientos de un producto bancario que tiene un cliente.
 * */

import com.nttdata.bootcamp.operationservice.domain.Statement;
import com.nttdata.bootcamp.operationservice.domain.dto.Balance;
import com.nttdata.bootcamp.operationservice.domain.dto.OperationData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditOperations {

    Mono<Statement> payCredit(OperationData operationData);
    Mono<Statement> consumeCredit(OperationData operationData);
    Mono<Balance> getBalance(String number);
    Flux<Statement> getStatements(String number);

}