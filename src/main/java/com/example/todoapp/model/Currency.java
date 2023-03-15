package com.example.todoapp.model;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("currencies")
public class Currency {
    @Id
    private String id;
    private Long timestamp;
    private BigDecimal lowestPrice;
    private BigDecimal highestPrice;
    private String name;
}
