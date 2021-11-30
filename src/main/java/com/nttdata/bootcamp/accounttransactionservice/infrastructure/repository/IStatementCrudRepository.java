package com.nttdata.bootcamp.accounttransactionservice.infrastructure.repository;

import com.nttdata.bootcamp.accounttransactionservice.infrastructure.model.dao.AccountStatementDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStatementCrudRepository extends ReactiveCrudRepository<AccountStatementDao, String> {
}
