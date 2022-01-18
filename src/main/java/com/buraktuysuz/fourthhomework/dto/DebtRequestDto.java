package com.buraktuysuz.fourthhomework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class DebtRequestDto {
    private LocalDate dueDate;
    private BigDecimal debtAmount;
    private Long userId;
}
