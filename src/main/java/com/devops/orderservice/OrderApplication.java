package com.devops.orderservice;

import java.util.UUID;
import java.util.logging.Logger;
import java.util.logging.Level;

public class OrderApplication {
    private static final Logger logger = Logger.getLogger(OrderApplication.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "=============================================");
        logger.log(Level.INFO, "OrderService Application Starting...");
        logger.log(Level.INFO, "DevOps Continuous Monitoring Active.");
        logger.log(Level.INFO, "=============================================");

        OrderProcessor processor = new OrderProcessor();
        long orderCount = 0;

        // Continuous execution loop simulating live e-commerce background traffic
        while (true) {
            orderCount++;
            String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
            double amount = 10.0 + (Math.random() * 490.0);

            processor.processOrder(orderId, amount);

            try {
                Thread.sleep(2000); // Process an order every 2 seconds
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "[LOG_ERROR] Processing loop interrupted", e);
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}