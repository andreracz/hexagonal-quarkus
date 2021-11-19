package com.hexagonal.adapter.controller;

import java.math.BigDecimal;

public class FinancialTransactionDTO {
    
    private String description;
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
