package com.buraktuysuz.fourthhomework.service.entityService;

import com.buraktuysuz.fourthhomework.entitiy.Collection;
import com.buraktuysuz.fourthhomework.reposity.CollectionRepository;
import org.springframework.stereotype.Service;

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
}
