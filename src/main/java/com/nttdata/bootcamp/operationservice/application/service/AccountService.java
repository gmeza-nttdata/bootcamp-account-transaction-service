package com.nttdata.bootcamp.operationservice.application.service;

import com.nttdata.bootcamp.operationservice.domain.entity.Account;
import org.springframework.stereotype.Component;

/** This class implements CRUD methods.
 *
 * @author gmeza
 *
 */
@Component
public interface AccountService extends IService<Account, String> {


}
