package com.example.todoapp.sevice;

import com.example.todoapp.model.Currency;
import java.util.List;

public interface CurrencyService {
    Currency getLowestPrice(String pair);

    Currency getHighestPrice(String pair);

    List<Currency> getPage(String pair, Integer page, Integer size);

    void appCurrenciesToDB();
}
