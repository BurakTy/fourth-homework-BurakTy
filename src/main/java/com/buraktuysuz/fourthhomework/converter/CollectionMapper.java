package com.buraktuysuz.fourthhomework.converter;


import com.buraktuysuz.fourthhomework.dto.CollectionResponseDto;
import com.buraktuysuz.fourthhomework.dto.DebtRequestDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.Collection;
import com.buraktuysuz.fourthhomework.entitiy.Debt;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionMapper {
    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    @Mapping(target = "collectionAmount", source = "debt.debtAmount")
    List<CollectionResponseDto> convertToCollectionResponseDtoList(List<Collection> collectionList);

    @AfterMapping()
    default void setNulls(@MappingTarget() final CollectionResponseDto collectionResponseDto,Collection collection){
        collectionResponseDto.setCollectionAmount(collection.getDebt().getDebtAmount());
    }
}
