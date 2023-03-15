package com.example.todoapp.repository;

import com.example.todoapp.model.Currency;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoCurrencyRepository extends MongoRepository<Currency, String> {
    Currency findFirstByNameIsLikeOrderByLowestPriceAsc(String name);

    Currency findFirstByNameIsLikeOrderByHighestPriceDesc(String name);

    List<Currency> findAllByNameIsLike(String name, Pageable pageable);

    @Aggregation(value
            = "{'$group': "
            + "     {_id: '$name',"
            + "         lowestPrice: {'$min': '$lowestPrice'},"
            + "         highestPrice: {'$max': '$highestPrice'}"
            + "     }"
            + "})")
    List<Currency> findMinMaxPricesByCurrency();
}
