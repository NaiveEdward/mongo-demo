package org.example.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private String id;
    private String userId;
    private String userName;
    private String itemId;
    private String itemName;
    private Integer count;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private LocalDateTime createTime;
}
