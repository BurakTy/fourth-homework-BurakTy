package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.reposity.DebtRepository;
import com.buraktuysuz.fourthhomework.service.entityService.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class DebtEntityService extends BaseEntityService<Debt, DebtRepository> {
    public DebtEntityService(DebtRepository dao) {
        super(dao);
    }

}
