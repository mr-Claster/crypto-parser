package com.example.todoapp.controller;

import com.example.todoapp.model.Currency;
import com.example.todoapp.sevice.CryptoCurrencyService;
import com.example.todoapp.sevice.FileService;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoCurrencyController {
    private final CryptoCurrencyService cryptoCurrencyService;
    private final FileService fileService;

    public CryptoCurrencyController(CryptoCurrencyService cryptoCurrencyService,
                                    FileService fileService) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.fileService = fileService;
    }

    @GetMapping("/minprice")
    public Currency getMinPrice(String name) {
        return cryptoCurrencyService.getLowestPrice(name);
    }

    @GetMapping("/maxprice")
    public Currency getMaxPrice(String name) {
        return cryptoCurrencyService.getHighestPrice(name);
    }

    @GetMapping
    public List<Currency> getCryptoCurrencyHistory(String name, Integer page, Integer size) {
        return cryptoCurrencyService.getPage(name, page, size);
    }

    @GetMapping("/csv")
    public ResponseEntity<Resource> getCsvReport() throws MalformedURLException {
        List<Currency> currencies = cryptoCurrencyService.findMinMaxPricesByCurrency();
        StringBuilder stringBuilder = new StringBuilder();
        currencies.forEach(c -> stringBuilder.append(c.getId())
                .append(", ")
                .append(c.getLowestPrice())
                .append(", ")
                .append(c.getHighestPrice())
                .append(System.lineSeparator()));
        File currencyCsvReport = fileService.getCryptoCurrencyCsvReport(stringBuilder.toString());
        Resource resource = new UrlResource(currencyCsvReport.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + currencyCsvReport.getName() + "\"")
                .body(resource);

    }
}
