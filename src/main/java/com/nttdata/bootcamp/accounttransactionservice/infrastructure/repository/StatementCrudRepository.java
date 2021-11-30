package com.nttdata.bootcamp.accounttransactionservice.infrastructure.repository;

import com.nttdata.bootcamp.accounttransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.accounttransactionservice.domain.AccountStatement;
import com.nttdata.bootcamp.accounttransactionservice.infrastructure.model.dao.AccountStatementDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StatementCrudRepository implements StatementRepository {

    @Autowired
    IStatementCrudRepository repository;


    @Override
    public Flux<AccountStatement> queryAll() {
        return repository.findAll()
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<AccountStatement> findById(String id) {
        return repository.findById(id)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<AccountStatement> create(AccountStatement accountStatement) {
        return repository.save(mapStatementToStatementDao(accountStatement))
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<AccountStatement> update(String id, AccountStatement accountStatement) {
        return repository.findById(id)
                .flatMap(s -> {
                    s.setId(id);
                    return Mono.just(mapStatementToStatementDao(accountStatement));
                }).flatMap(repository::save)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    private AccountStatement mapStatementDaoToStatement(AccountStatementDao accountStatementDao) {
        AccountStatement accountStatement = new AccountStatement();
        BeanUtils.copyProperties(accountStatementDao, accountStatement);
        return accountStatement;
    }

    private AccountStatementDao mapStatementToStatementDao(AccountStatement accountStatement) {
        AccountStatementDao accountStatementDao = new AccountStatementDao();
        BeanUtils.copyProperties(accountStatement, accountStatementDao);
        return accountStatementDao;
    }


}
