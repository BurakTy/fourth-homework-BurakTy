package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import com.buraktuysuz.fourthhomework.reposity.DebtRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        List<Debt> returnDebtList = new ArrayList<>();
        List<Debt> debtList = this.getDao().findAllUserDebts(id);
        returnDebtList.addAll(debtList);
        return addDebtList(returnDebtList,debtList);
    }

    public BigDecimal findUserDebtsAmountTotalwWithInterest(Long id) {
        List<Debt> debtList = findAllUserDebts(id);
        BigDecimal total = BigDecimal.ZERO;
        for (Debt d : debtList) {
            total=total.add(d.getDebtAmount());
        }
        return total;
    }

    public List<Debt> findAllUserDebtsOverdue(Long id) {
        List<Debt> returnDebtList = new ArrayList<>();
        List<Debt> debtList = this.getDao().findAllUserDebtsOverdue(id, LocalDate.now());
        returnDebtList.addAll(debtList);
        return addDebtList(returnDebtList, debtList);
    }


    public BigDecimal findAllUserDebtsOverdueAmountTotal(Long id) {
        List<Debt> debtList = findAllUserDebtsOverdue(id);
        BigDecimal total = BigDecimal.ZERO;
        for (Debt d : debtList) {
            total=total.add(d.getDebtAmount());
        }
        return total;
    }

    public BigDecimal findUserCurrentInterestTotal(Long id) {
        List<Debt> debtList = this.getDao().findAllUserDebtsOverdue(id, LocalDate.now());
        List<Debt> interestList =addDebtList(new ArrayList<>(),debtList);
        BigDecimal total = BigDecimal.ZERO;
        for (Debt d : interestList) {
            total=total.add(d.getDebtAmount());
        }
        return total;
    }

    public Debt addInterestDebt(long days, Debt debt) {
        float rate = 1.5F;
        if (debt.getDueDate().isAfter(LocalDate.parse("2018-01-01"))) {
            rate = 2.0F;
        }
        BigDecimal total = BigDecimal.valueOf(days * rate);
        total = total.compareTo(BigDecimal.valueOf(1)) != 1 ? BigDecimal.valueOf(1) : total;
        Debt interestDebt = findBySuperDebtId(debt.getId());
        if (interestDebt == null) {
            interestDebt = new Debt();
        }
        interestDebt.setDebtType(EnumDebtType.INTRESET);
        interestDebt.setRemainingDebtAmount(total);
        interestDebt.setDebtAmount(total);
        interestDebt.setCreateDate(LocalDate.now());
        interestDebt.setUser(debt.getUser());
        interestDebt.setDebt(debt);
        interestDebt.setDueDate(debt.getDueDate());
        return save(interestDebt);
    }

    private long callOverdueDays(LocalDate date) {
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(date, today);
    }

    private List<Debt> addDebtList(List<Debt> returnDebtList, List<Debt> debtList) {
        for (Debt d : debtList) {
            long diff = callOverdueDays(d.getDueDate());
            if (diff > 0) {
                Debt interestDebt = addInterestDebt(diff, d);
                returnDebtList.add(interestDebt);
            }
        }

        return returnDebtList;
    }


}
