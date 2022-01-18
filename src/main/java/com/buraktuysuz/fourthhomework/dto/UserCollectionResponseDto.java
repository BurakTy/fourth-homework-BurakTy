package com.buraktuysuz.fourthhomework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserCollectionResponseDto {
    private BigDecimal debtAmount;
    private BigDecimal collectionAmount;
    private Date collectionDate;
    private String userFullName;
}
