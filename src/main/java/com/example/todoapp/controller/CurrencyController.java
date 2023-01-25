package com.example.todoapp.controller;

import com.example.todoapp.model.Currency;
import com.example.todoapp.sevice.CurrencyService;
import com.example.todoapp.sevice.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/cryptocurrencies")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final FileService fileService;

    public CurrencyController(CurrencyService currencyService,
                              FileService fileService) {
        this.currencyService = currencyService;
        this.fileService = fileService;
    }

    @GetMapping("/minprice")
    Currency getLowestPrice(String pair) {
        return currencyService.getLowestPrice(pair);
    }

    @GetMapping("/maxprice")
    Currency getHighestPrice(String pair) {
        return currencyService.getHighestPrice(pair);
    }

    @GetMapping
    List<Currency> getSelectPage(String pair, Integer page, Integer size) {
        return currencyService.getPage(pair, page, size);
    }

    @GetMapping("/csv")
    ResponseEntity<Resource> getCSVReport() throws MalformedURLException {
        File currencyCSVReport = fileService.getCurrencyCSVReport();
        Resource resource = new UrlResource(currencyCSVReport.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + currencyCSVReport.getName() + "\"")
                .body(resource);

    }
}
