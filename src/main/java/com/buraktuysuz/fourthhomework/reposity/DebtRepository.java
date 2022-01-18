package com.buraktuysuz.fourthhomework.reposity;

import com.buraktuysuz.fourthhomework.entitiy.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt,Long> {

    List<Debt> findAllByCreateDateBetweenAndDebtAmountGreaterThan(LocalDate firstDate, LocalDate lastDate, BigDecimal amount);

    Debt findFirstByDebt_Id(Long id);

    @Query("select debt from Debt debt where debt.debt.id =?1")
    Debt findBySuperDebtId(Long id);



    @Query("select debt from Debt debt where debt.debtAmount>0 and debt.user.id=?1")
    List<Debt> findAllUserDebts(Long id);

    @Query("select sum(debt.debtAmount) from Debt debt where debt.user.id=?1 and debt.remainingDebtAmount >= 0")
    BigDecimal findAllUserDebtsAmountTotal(Long id);

    @Query("select debt from Debt debt where debt.remainingDebtAmount >= 0 and debt.user.id=?1 and debt.dueDate<?2")
    List<Debt> findAllUserDebtsOverdue(Long id,LocalDate now);

    @Query("SELECT SUM(debt.debtAmount) FROM Debt debt where  debt.remainingDebtAmount >= 0  and debt.user.id=?1 and debt.dueDate<?2")
    BigDecimal findAllUserDebtsOverdueAmountTotal(Long id,LocalDate now);


}
