package com.buraktuysuz.fourthhomework.reposity;

import com.buraktuysuz.fourthhomework.entitiy.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection,Long> {

    List<Collection> findAllByCollectionDateBetween(Date firstDate, Date lastDate);

    @Query("select coll from Collection coll where coll.debt.user.id=?1")
    List<Collection> findAllCollectionByUserId(Long id);

    @Query("select sum(coll.debt.debtAmount) from Collection coll " +
            "where coll.debt.debtType= com.buraktuysuz.fourthhomework.enums.EnumDebtType.INTRESET and " +
            "coll.debt.user.id =?1 " )
    BigDecimal findAllUserDebtsAmountTotal(Long id);



}
