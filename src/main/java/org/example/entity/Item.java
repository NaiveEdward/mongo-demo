package org.example.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer sku;
    private String detail;
}
