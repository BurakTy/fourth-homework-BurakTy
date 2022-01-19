package com.buraktuysuz.fourthhomework.service;

import com.buraktuysuz.fourthhomework.converter.DebtMapper;
import com.buraktuysuz.fourthhomework.dto.DebtRequestDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.entitiy.User;
import com.buraktuysuz.fourthhomework.enums.EnumDebtType;
import com.buraktuysuz.fourthhomework.exception.BadRequestException;
import com.buraktuysuz.fourthhomework.service.entityService.DebtEntityService;
import com.buraktuysuz.fourthhomework.service.entityService.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DebtService {
    private DebtEntityService debtEntityService;
    private UserEntityService userService;

    public DebtResponseDto save(DebtRequestDto debtRequestDto) {
        User user = userService.findById(debtRequestDto.getUserId());
        if (user == null) {
            throw new BadRequestException("No debtor user found for this id " + debtRequestDto.getUserId());
        }
        Debt debt = DebtMapper.INSTANCE.convertToDebtRequestDto(debtRequestDto);
        debt.setCreateDate(LocalDate.now());
        debt.setDebtType(EnumDebtType.NORMAL);
        debt.setRemainingDebtAmount(debt.getDebtAmount());
        debt = debtEntityService.save(debt);
        debt.setUser(user);
        DebtResponseDto debtResponseDto = DebtMapper.INSTANCE.convertToDebt(debt);
        return debtResponseDto;
    }

    @Transactional
    public void deleteById(Long id) {
        Debt debt = debtEntityService.findById(id);
        if (debt == null) {
            throw new BadRequestException("No dept found for this id " + id);
        }
        if (debt.getRemainingDebtAmount() == BigDecimal.ZERO) {
            debtEntityService.delete(debt);
        } else {
            throw new BadRequestException("this " + id + " debt has not been collected");
        }
    }

    public List<DebtResponseDto> findAll() {
        List<Debt> debtList = debtEntityService.findAll();
        List<DebtResponseDto> responseDtoList = DebtMapper.INSTANCE.convertToDebtResponseDtoList(debtList);
        return responseDtoList;
    }

    public List<DebtResponseDto> findAllBetweenDate(LocalDate firstDate, LocalDate lastDate) {
        List<Debt> debtList = debtEntityService.findAllBetweenDate(firstDate, lastDate);
        List<DebtResponseDto> responseDtoList = DebtMapper.INSTANCE.convertToDebtResponseDtoList(debtList);
        return responseDtoList;
    }

    public List<DebtResponseDto> findAllUserDebts(Long id) {
        List<Debt> debtList = debtEntityService.findAllUserDebts(id);
        List<DebtResponseDto> responseDtoList = DebtMapper.INSTANCE.convertToDebtResponseDtoList(debtList);
        return responseDtoList;
    }

    public List<DebtResponseDto> findAllUserDebtsOverdue(Long id) {
        List<Debt> debtList = debtEntityService.findAllUserDebtsOverdue(id);
        List<DebtResponseDto> responseDtoList = DebtMapper.INSTANCE.convertToDebtResponseDtoList(debtList);
        return responseDtoList;
    }

    public BigDecimal findUserDebtsAmountTotalwWithInterest(Long id) {
        return debtEntityService.findUserDebtsAmountTotalwWithInterest(id);
    }

    public BigDecimal findAllUserDebtsOverdueAmountTotal(Long id) {
        return  debtEntityService.findAllUserDebtsOverdueAmountTotal(id);
    }


    public BigDecimal findUserCurrentInterestTotal(Long id) {
        return  debtEntityService.findUserCurrentInterestTotal(id);
    }
}
