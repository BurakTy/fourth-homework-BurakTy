package com.buraktuysuz.fourthhomework.converter;


import com.buraktuysuz.fourthhomework.dto.DebtRequestDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtMapper {
    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

    Debt convertToDebtRequestDto(DebtRequestDto debtRequestDto);
    DebtResponseDto convertToDebt(Debt debt);
    List<DebtResponseDto> convertToDebtResponseDtoList(List<Debt> debtList);

}
