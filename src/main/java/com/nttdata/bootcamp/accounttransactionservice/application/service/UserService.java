package com.nttdata.bootcamp.accounttransactionservice.application.service;

import com.nttdata.bootcamp.accounttransactionservice.domain.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends IService<User,Integer> {
}
