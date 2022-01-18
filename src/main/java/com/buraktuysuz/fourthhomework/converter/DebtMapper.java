package com.buraktuysuz.fourthhomework.converter;


import com.buraktuysuz.fourthhomework.dto.DebtRequestDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.entitiy.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtMapper {
    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

    @Mapping(target = "user.id", source = "userId")
    Debt convertToDebtRequestDto(DebtRequestDto debtRequestDto);


    DebtResponseDto convertToDebt(Debt debt);
    List<DebtResponseDto> convertToDebtResponseDtoList(List<Debt> debtList);

    @AfterMapping()
    default void setNulls(@MappingTarget() final Debt debt,DebtRequestDto debtRequestDto){
        if(debtRequestDto.getUserId()==null){
            debt.setDebt(null);
        }
    }

     @AfterMapping()
    default void setDebtResponseDto(@MappingTarget() final DebtResponseDto debtResponseDto,Debt debt ){
        debtResponseDto.setUserId(debt.getUser().getId());
         debtResponseDto.setUserFullName(debt.getUser().getFirstName() +" "+ debt.getUser().getLastName());
         debtResponseDto.setDebtType(debt.getDebtType().name());
         if(debt.getDebt()!=null){
             debtResponseDto.setSuperDeptId(debt.getDebt().getId());
         }
         debtResponseDto.setDueDate(debt.getDueDate());
     }

}
