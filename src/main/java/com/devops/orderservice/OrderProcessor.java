package com.devops.orderservice;

import java.util.logging.Logger;
import java.util.logging.Level;

public class OrderProcessor {
    private static final Logger logger = Logger.getLogger(OrderProcessor.class.getName());

    public boolean processOrder(String orderId, double amount) {
        if (orderId == null || orderId.trim().isEmpty()) {
            logger.log(Level.WARNING, "[LOG_WARN] Invalid order processing attempt: Null/Empty Order ID");
            return false;
        }

        if (amount <= 0) {
            logger.log(Level.WARNING, "[LOG_WARN] Invalid order processing attempt: Amount must be positive. Order ID: {0}", orderId);
            return false;
        }

        // Simulating processing logic
        logger.log(Level.INFO, "[LOG_INFO] Processing order successfully | OrderID: {0} | Amount: ${1}", new Object[]{orderId, amount});
        return true;
    }

    public double calculateTotalWithTax(double subtotal, double taxRate) {
        if (subtotal < 0 || taxRate < 0) {
            throw new IllegalArgumentException("Subtotal and Tax Rate must be non-negative");
        }
        return Math.round((subtotal + (subtotal * taxRate)) * 100.0) / 100.0;
    }
}