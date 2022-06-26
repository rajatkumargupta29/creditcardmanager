package com.creditcardprovider.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String errorCd;
    private String errorMessage;
}
