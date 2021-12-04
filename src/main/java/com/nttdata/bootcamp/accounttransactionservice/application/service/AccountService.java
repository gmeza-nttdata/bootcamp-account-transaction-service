package com.nttdata.bootcamp.accounttransactionservice.application.service;

import com.nttdata.bootcamp.accounttransactionservice.domain.entity.account.Account;
import org.springframework.stereotype.Component;

/** This class implements CRUD methods.
 *
 * @author gmeza
 *
 */
@Component
public interface AccountService extends IService<Account, String> {


}
