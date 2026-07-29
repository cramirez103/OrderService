package com.devops.orderservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorTest {

    @Test
    public void testValidOrderProcessing() {
        OrderProcessor processor = new OrderProcessor();
        boolean result = processor.processOrder("ORD-12345", 99.99);
        assertTrue(result, "Valid order should be processed successfully");
    }

    @Test
    public void testInvalidAmountProcessing() {
        OrderProcessor processor = new OrderProcessor();
        boolean result = processor.processOrder("ORD-12345", -10.00);
        assertFalse(result, "Negative order amounts should fail processing");
    }

    @Test
    public void testCalculateTotalWithTax() {
        OrderProcessor processor = new OrderProcessor();
        double total = processor.calculateTotalWithTax(100.00, 0.07);
        assertEquals(107.00, total, 0.001, "100 + 7% tax should equal 107.00");
    }
}