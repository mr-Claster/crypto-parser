package com.example.todoapp.sevice.impl;

import com.example.todoapp.model.PairsOfCurrencies;
import com.example.todoapp.repository.PairsOfCurrenciesRepository;
import com.example.todoapp.sevice.PairsOfCurrenciesService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PairsOfCurrenciesServiceImpl implements PairsOfCurrenciesService {
    private final PairsOfCurrenciesRepository pairsOfCurrenciesRepository;

    public PairsOfCurrenciesServiceImpl(PairsOfCurrenciesRepository pairsOfCurrenciesRepository) {
        this.pairsOfCurrenciesRepository = pairsOfCurrenciesRepository;
    }

    public List<PairsOfCurrencies> getAll() {
        return pairsOfCurrenciesRepository.findAll();
    }
}
