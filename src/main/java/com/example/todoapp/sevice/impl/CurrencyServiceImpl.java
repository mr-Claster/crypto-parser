package com.example.todoapp.sevice.impl;

import com.example.todoapp.model.Currency;
import com.example.todoapp.model.PairsOfCurrencies;
import com.example.todoapp.repository.CurrencyRepository;
import com.example.todoapp.sevice.CurrencyService;
import com.google.gson.Gson;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final String URL = "https://cex.io/api/ticker/";

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getLowestPrice(String pair) {
        return currencyRepository.findFirstByPairOrderByLowAsc(pair);
    }

    @Override
    public Currency getHighestPrice(String pair) {
        return currencyRepository.findFirstByPairOrderByHighDesc(pair);
    }

    @Override
    public List<Currency> getPage(String pair, Integer page, Integer size) {
        return currencyRepository.findAllByPair(pair, PageRequest.of(page, size));
    }


    @Override
    @Scheduled(fixedDelay = 5000L)
    public void appCurrenciesToDB() {
        try {
            for(String pair: PairsOfCurrencies.pairs) {
                URL urlBTS = new URL(URL + pair.replace(':','/'));
                HttpURLConnection btsConnection = (HttpURLConnection) urlBTS.openConnection();
                btsConnection.setRequestMethod("GET");
                currencyRepository.save(readeDataFromURL(btsConnection));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Currency readeDataFromURL(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String json = in.readLine();
        Currency currency = new Gson().fromJson(json, Currency.class);
        in.close();
        return currency;
    }
}
