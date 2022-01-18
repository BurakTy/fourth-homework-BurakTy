package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import com.buraktuysuz.fourthhomework.reposity.DebtRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class DebtEntityService extends BaseEntityService<Debt, DebtRepository> {
    public DebtEntityService(DebtRepository dao) {
        super(dao);
    }

    public List<Debt> findAllBetweenDate(LocalDate firstDate, LocalDate lastDate) {
        return this.getDao().findAllByCreateDateBetweenAndDebtAmountGreaterThan(firstDate, lastDate, BigDecimal.ZERO);
    }

    public Debt findBySuperDebtId(Long id) {
        return this.getDao().findFirstByDebt_Id(id);
    }

    public List<Debt> findAllUserDebts(Long id) {
        return this.getDao().findAllUserDebts(id);
    }

    public BigDecimal findAllUserDebtsAmountTotal(Long id) {
        return this.getDao().findAllUserDebtsAmountTotal(id);
    }

    public List<Debt> findAllUserDebtsOverdue(Long id) {
        return this.getDao().findAllUserDebtsOverdue(id, LocalDate.now());
    }

    public BigDecimal findAllUserDebtsOverdueAmountTotal(Long id) {
        return  this.getDao().findAllUserDebtsOverdueAmountTotal(id, LocalDate.now());
    }

    public Debt addInterestDebt(long days,Debt debt) {
        float rate = 1.5F;
        if(debt.getDueDate().isAfter(LocalDate.parse("2018-01-01"))){
            rate=2.0F;
        }
        BigDecimal total= BigDecimal.valueOf(days * rate);
        Debt interestDebt= findBySuperDebtId(debt.getId());
        if(interestDebt==null){
            interestDebt=new Debt();
        }
        interestDebt.setDebtType(EnumDebtType.INTRESET);
        interestDebt.setRemainingDebtAmount(total);
        interestDebt.setDebtAmount(total);
        interestDebt.setCreateDate(LocalDate.now());
        interestDebt.setUser(debt.getUser());
        interestDebt.setDebt(debt);
        interestDebt.setDueDate(debt.getDueDate());
        return  save(interestDebt);
    }


}
