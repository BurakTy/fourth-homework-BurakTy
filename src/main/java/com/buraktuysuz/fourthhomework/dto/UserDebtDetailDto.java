package com.buraktuysuz.fourthhomework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserDebtDetailDto {
    private String fullName;
    private Date dueDate;
    private BigDecimal debtAmount;
    private BigDecimal interest;
    private String debtTypeName;
}
