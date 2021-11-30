package com.nttdata.bootcamp.accounttransactionservice.application;


/*
 * Un cliente puede hacer depósitos y retiros de sus cuentas bancarias.
 *
 * El sistema debe permitir consultar los saldos disponibles en sus cuentas bancarias
 *
 * El sistema debe permitir consultar todos los movimientos de un producto bancario
 * que tiene un cliente.
 *
 */

import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountOperations {

    Mono<AccountStatement> deposit(OperationData operationData);
    Mono<AccountStatement> withdraw(OperationData operationData);
    Flux<AccountStatement> getStatements(String accountNumber);

}
