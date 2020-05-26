package org.example.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemListDTO {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer sku;
}
