package com.buraktuysuz.fourthhomework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DebtRequestDto {
    private Date dueDate;
    private BigDecimal debtAmount;
    private Long userId;
}
