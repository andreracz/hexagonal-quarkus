package com.hexagonal.core.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TransactionTest {

    
    @Test
    public void testConstructorEmptyTransactionId() {
        try {
            new Transaction(null, null, null, null, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("TransactionId cannot be null", e.getMessage());
        }
    }


    @Test
    public void testConstructorEmptyValue() {
        try {
            new Transaction("1", null, null, null, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("Value cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConstructorZeroValue() {
        try {
            new Transaction("1", new BigDecimal(0), null, null, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("Value cannot be zero", e.getMessage());
        }
    }

    @Test
    public void testConstructorNegativeValue() {
        try {
            new Transaction("1", new BigDecimal(-1), null, null, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("Value cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testConstructorNullDescription() {
        try {
            new Transaction("1", new BigDecimal(1), null, null, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("Description cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConstructorNullType() {
        try {
            new Transaction("1", new BigDecimal(1), "desc", null, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("TransactionType cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConstructorNullDate() {
        try {
            new Transaction("1", new BigDecimal(1), "desc", TransactionType.Credit, null);
            Assertions.fail();
        } catch(IllegalArgumentException e) {
            Assertions.assertEquals("TransactionDate cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConstructorOk() {
        Date date = new Date();
        Transaction transaction = new Transaction("1", new BigDecimal(1), "desc", TransactionType.Credit, date);
        Assertions.assertEquals("1", transaction.getTransactionId());
        Assertions.assertEquals(new BigDecimal(1), transaction.getValue());
        Assertions.assertEquals("desc", transaction.getDescription());
        Assertions.assertEquals(TransactionType.Credit, transaction.getTransactionType());
        Assertions.assertEquals(date, transaction.getTransactionDate());
    }

    
}
