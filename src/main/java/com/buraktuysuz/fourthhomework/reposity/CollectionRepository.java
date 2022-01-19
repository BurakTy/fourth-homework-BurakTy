package com.buraktuysuz.fourthhomework.reposity;

import com.buraktuysuz.fourthhomework.entitiy.Collection;
import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection,Long> {

    List<Collection> findAllByCollectionDateBetween(Date firstDate, Date lastDate);
    List<Collection> findAllByDebt_User_Id(Long id);
    List<Collection> findAllByDebt_User_IdAndDebt_DebtType(Long id, EnumDebtType type);

    @Query("select sum(coll.debt.debtAmount) from Collection coll " +
            "where coll.debt.debtType= com.buraktuysuz.fourthhomework.enums.EnumDebtType.INTRESET and " +
            "coll.debt.user.id =?1 " )
    BigDecimal findAllUserDebtsAmountTotal(Long id);



}
