package com.nttdata.bootcamp.accounttransactionservice.infrastructure.rest;

import com.nttdata.bootcamp.accounttransactionservice.application.AccountOperations;
import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import com.nttdata.bootcamp.accounttransactionservice.domain.dto.OperationData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/account-transactions")
@RequiredArgsConstructor
public class AccountOperationController {
    private final AccountOperations accountOperations;

    @PostMapping(value = "deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountStatement>> deposit(@RequestBody OperationData operationData) {
        if (operationData.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Amount");

        return accountOperations.deposit(operationData)
                .doOnError(Throwable::printStackTrace)
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> Mono.just(ResponseEntity.badRequest()
                        .body(new AccountStatement(throwable.toString())))
                );
    }

    @PostMapping(value = "withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountStatement>> withdraw(@RequestBody OperationData operationData) {
        if (operationData.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Amount");

        return accountOperations.withdraw(operationData)
                .doOnError(Throwable::printStackTrace)
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> Mono.just(ResponseEntity.badRequest()
                        .body(new AccountStatement(throwable.toString())))
                );
    }


    @GetMapping(value = "statements/{number}")
    public Flux<AccountStatement> getStatementsByNumber(@PathVariable String number) {
        return accountOperations.getStatements(number);
    }

    @GetMapping(value = "statements")
    public Flux<AccountStatement> getStatements() {
        return accountOperations.getAllStatements();
    }

}
