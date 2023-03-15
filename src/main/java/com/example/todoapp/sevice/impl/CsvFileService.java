package com.example.todoapp.sevice.impl;

import com.example.todoapp.sevice.FileService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.springframework.stereotype.Service;

@Service
public class CsvFileService implements FileService {
    private static final String REPORT_FILE_NAME = "report.csv";

    @Override
    public File getCryptoCurrencyCsvReport(String report) {
        File csvReport = new File(REPORT_FILE_NAME);
        try (PrintWriter out = new PrintWriter(csvReport)) {
            out.printf(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("can't write data to file", e);
        }
        return csvReport;
    }
}
