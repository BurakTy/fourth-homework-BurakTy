package com.buraktuysuz.fourthhomework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CollectionResponseDto {
    private BigDecimal collectionAmount;
    private Date collectionDate;
}
