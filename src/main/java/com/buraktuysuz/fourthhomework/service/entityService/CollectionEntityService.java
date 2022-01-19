package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.Collection;
import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import com.buraktuysuz.fourthhomework.reposity.CollectionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CollectionEntityService extends BaseEntityService<Collection, CollectionRepository> {
    public CollectionEntityService(CollectionRepository dao) {
        super(dao);
    }
    public List<Collection> findAllBetweenDate(LocalDate firstDate, LocalDate lastDate) {
        return this.getDao().findAllByCollectionDateBetween(convertToDateViaSqlDate(firstDate), convertToDateViaSqlDate(lastDate));
    }


    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public List<Collection> findCollectionByUserId(Long id) {
        return this.getDao().findAllByDebt_User_Id(id);
    }
    public BigDecimal findCollectionInterestTotalByUserId(Long id) {
        return this.getDao().findAllUserDebtsAmountTotal(id);
    }

    public List<Collection> findCollectionInterestByUserId(Long id) {
        return  this.getDao().findAllByDebt_User_IdAndDebt_DebtType(id, EnumDebtType.INTRESET);
    }


}
