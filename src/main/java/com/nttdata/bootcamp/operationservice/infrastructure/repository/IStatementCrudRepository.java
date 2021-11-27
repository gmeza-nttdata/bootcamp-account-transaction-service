package com.nttdata.bootcamp.operationservice.infrastructure.repository;

import com.nttdata.bootcamp.operationservice.infrastructure.model.dao.StatementDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStatementCrudRepository extends ReactiveCrudRepository<StatementDao, String> {
}
