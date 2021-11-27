package com.nttdata.bootcamp.operationservice.application.service;

import com.nttdata.bootcamp.operationservice.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends IService<User,Integer> {
}
