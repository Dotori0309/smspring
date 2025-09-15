package edu.sm.app.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order {
    private int orderId;
    private String customerId;
    private int orderPrice;
    private LocalDateTime orderDate;
}
