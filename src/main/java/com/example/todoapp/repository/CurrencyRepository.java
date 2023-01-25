package com.example.todoapp.repository;

import com.example.todoapp.model.Currency;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CurrencyRepository extends MongoRepository<Currency, String> {
    Currency findFirstByPairOrderByLowAsc(String pair);

    Currency findFirstByPairOrderByHighDesc(String pair);

    List<Currency> findAllByPair(String pair, Pageable pageable);
}
