package com.example.todoapp.sevice.impl;

import com.example.todoapp.model.PairsOfCurrencies;
import com.example.todoapp.sevice.CurrencyService;
import com.example.todoapp.sevice.FileService;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Service
public class FileServiceImp implements FileService {
    private final CurrencyService currencyService;

    public FileServiceImp(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public File getCurrencyCSVReport() {
        File csvReport = new File("report.csv");
        try (PrintWriter out = new PrintWriter(csvReport)) {
            for(String pair: PairsOfCurrencies.pairs) {
                out.printf("%s, %f, %f\n", pair,
                        currencyService.getHighestPrice(pair).getHigh(),
                        currencyService.getLowestPrice(pair).getLow());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return csvReport;
    }
}
