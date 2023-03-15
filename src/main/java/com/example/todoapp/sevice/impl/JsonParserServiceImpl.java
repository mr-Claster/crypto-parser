package com.example.todoapp.sevice.impl;

import com.example.todoapp.model.Currency;
import com.example.todoapp.model.PairsOfCurrencies;
import com.example.todoapp.repository.CryptoCurrencyRepository;
import com.example.todoapp.sevice.JsonParserService;
import com.example.todoapp.sevice.PairsOfCurrenciesService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JsonParserServiceImpl implements JsonParserService {
    @Value("${crypto.url}")
    private String url;

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final PairsOfCurrenciesService pairsOfCurrenciesService;

    public JsonParserServiceImpl(CryptoCurrencyRepository cryptoCurrencyRepository,
                                 PairsOfCurrenciesService pairsOfCurrenciesService) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.pairsOfCurrenciesService = pairsOfCurrenciesService;
    }

    @Override
    @Scheduled(fixedDelay = 10000L)
    public void appCurrenciesToDB() {
        try {
            for (PairsOfCurrencies pair: pairsOfCurrenciesService.getAll()) {
                URL url = new URL(this.url + pair.getName().replace(':','/'));
                HttpURLConnection btsConnection = (HttpURLConnection) url.openConnection();
                btsConnection.setRequestMethod(HttpMethod.GET.name());
                cryptoCurrencyRepository.save(readeCurrencyFromUrl(btsConnection));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Currency readeCurrencyFromUrl(HttpURLConnection connection) throws IOException {
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String json = reader.readLine();
        json = json.replace("pair", "name");
        json = json.replace("low", "lowestPrice");
        json = json.replace("high", "highestPrice");
        Currency currency = new Gson().fromJson(json, Currency.class);
        reader.close();
        return currency;
    }
}
