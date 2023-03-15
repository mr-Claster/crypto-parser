package com.example.todoapp.sevice.impl;

import com.example.todoapp.model.Currency;
import com.example.todoapp.repository.CryptoCurrencyRepository;
import com.example.todoapp.sevice.CryptoCurrencyService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public CryptoCurrencyServiceImpl(CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    @Override
    public Currency getLowestPrice(String pair) {
        return cryptoCurrencyRepository.findFirstByNameIsLikeOrderByLowestPriceAsc(pair);
    }

    @Override
    public Currency getHighestPrice(String pair) {
        return cryptoCurrencyRepository.findFirstByNameIsLikeOrderByHighestPriceDesc(pair);
    }

    @Override
    public List<Currency> getPage(String pair, Integer page, Integer size) {
        return cryptoCurrencyRepository.findAllByNameIsLike(pair, PageRequest.of(page, size));
    }

    @Override
    public List<Currency> findMinMaxPricesByCurrency() {
        return cryptoCurrencyRepository.findMinMaxPricesByCurrency();
    }
}
