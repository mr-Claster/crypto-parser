package com.example.todoapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("pairs")
public class PairsOfCurrencies {
    @Id
    private String id;
    private String name;
}
