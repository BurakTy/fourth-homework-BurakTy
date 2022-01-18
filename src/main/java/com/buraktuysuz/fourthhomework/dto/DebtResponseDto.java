package com.buraktuysuz.fourthhomework.dto;

import com.buraktuysuz.fourthhomework.entitiy.Debt;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class DebtResponseDto {
    private Long id;
    private BigDecimal debtAmount;
    private LocalDate createDate;
    private LocalDate dueDate;
    private Long userId;
    private Long superDeptId;
    private String userFullName;
    private String debtType;
}
