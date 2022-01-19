package com.buraktuysuz.fourthhomework.service;

import com.buraktuysuz.fourthhomework.converter.CollectionMapper;
import com.buraktuysuz.fourthhomework.converter.DebtMapper;
import com.buraktuysuz.fourthhomework.converter.UserMapper;
import com.buraktuysuz.fourthhomework.dto.CollectionResponseDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.dto.UserRequestDto;
import com.buraktuysuz.fourthhomework.dto.UserResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.Collection;
import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.entitiy.User;
import com.buraktuysuz.fourthhomework.exception.BadRequestException;
import com.buraktuysuz.fourthhomework.service.entityService.CollectionEntityService;
import com.buraktuysuz.fourthhomework.service.entityService.DebtEntityService;
import com.buraktuysuz.fourthhomework.service.entityService.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CollectionService {
    private CollectionEntityService collectionEntityService;
    private DebtEntityService debtEntityService;

    @Transactional
    public List<CollectionResponseDto> payByDebtId(Long id) {
        List<Collection> collectionList=new ArrayList<>();
        Debt debt = debtEntityService.findById(id);
        if(debt.getRemainingDebtAmount().doubleValue()==BigDecimal.valueOf(0.00).doubleValue()){
            throw new BadRequestException("No debt to be paid for this id " +id);
        }
        debt.setRemainingDebtAmount(BigDecimal.ZERO);
        Collection collection=new Collection();
        collection.setDebt(debt);
        collection.setCollectionDate(new Date());
        collection=collectionEntityService.save(collection);
        collectionList.add(collection);
        debtEntityService.save(debt);
        long diff=callOverdueDays(debt.getDueDate());
        if(diff>0){
           Debt interestDebt= debtEntityService.addInterestDebt(diff,debt);
           interestDebt.setRemainingDebtAmount(BigDecimal.ZERO);
           Collection interestCollection=new Collection();
           interestCollection.setDebt(interestDebt);
           interestCollection.setCollectionDate(new Date());
           interestCollection=collectionEntityService.save(interestCollection);
           collectionList.add(interestCollection);
           debtEntityService.save(interestDebt);
        }
        List<CollectionResponseDto>  responseDtoList= CollectionMapper.INSTANCE.convertToCollectionResponseDtoList(collectionList);
        return responseDtoList;
    }

    public List<CollectionResponseDto> findAll() {
        List<Collection> collectionList = collectionEntityService.findAll();
        List<CollectionResponseDto>  responseDtoList= CollectionMapper.INSTANCE.convertToCollectionResponseDtoList(collectionList);
        return responseDtoList;
    }

    public List<CollectionResponseDto> findAllBetweenDate(LocalDate firstDate, LocalDate lastDate) {
        List<Collection> collectionList = collectionEntityService.findAllBetweenDate(firstDate,lastDate);
        List<CollectionResponseDto> responseDtoList = CollectionMapper.INSTANCE.convertToCollectionResponseDtoList(collectionList);
        return responseDtoList;
    }

    public List<CollectionResponseDto> findCollectionByUserId(Long id) {
        List<Collection> collectionList = collectionEntityService.findCollectionByUserId(id);
        List<CollectionResponseDto> responseDtoList = CollectionMapper.INSTANCE.convertToCollectionResponseDtoList(collectionList);
        return responseDtoList;
    }

    public BigDecimal findCollectionInterestTotalByUserId(Long id) {
        return collectionEntityService.findCollectionInterestTotalByUserId(id);
    }

    private long callOverdueDays(LocalDate date){
        LocalDate today = LocalDate.now();
        return  ChronoUnit.DAYS.between(date,today);
    }

    public List<CollectionResponseDto> findCollectionInterestByUserId(Long id) {
        List<Collection> collectionList = collectionEntityService.findCollectionInterestByUserId(id);
        List<CollectionResponseDto> responseDtoList = CollectionMapper.INSTANCE.convertToCollectionResponseDtoList(collectionList);
        return responseDtoList;
    }

}
