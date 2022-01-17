package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.User;
import com.buraktuysuz.fourthhomework.reposity.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    public UserEntityService(UserRepository dao) {
        super(dao);
    }

}
