package com.example.todoapp.repository;

import com.example.todoapp.model.PairsOfCurrencies;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PairsOfCurrenciesRepository extends MongoRepository<PairsOfCurrencies, String> {
}
