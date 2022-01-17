package com.buraktuysuz.fourthhomework.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private boolean success=false;
    private int code;
    private String detail;

}
