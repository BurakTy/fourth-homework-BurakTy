package com.buraktuysuz.fourthhomework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDebtDetailDto {
    private String fullName;
    private LocalDate dueDate;
    private BigDecimal debtAmount;
    private BigDecimal interest;
    private String debtTypeName;
}
