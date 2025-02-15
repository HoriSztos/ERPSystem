package com.example.erpsystem.finances;

import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.service.PdfReportService;
import com.example.erpsystem.finances.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/finance")
public class TransactionController {
    private final TransactionService transactionService;
    private final PdfReportService pdfReportService;

    public TransactionController(TransactionService transactionService, PdfReportService pdfReportService) {
        this.transactionService = transactionService;
        this.pdfReportService = pdfReportService;
    }


    @GetMapping
    public String home(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("dailyProfit", transactionService.calculateDailyProfit(today));
        model.addAttribute("transactions", transactionService.getRecentTransactions());
        return "finance_home";
    }

    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "finance_transactions";
    }

    @GetMapping("/reports")
    public String reports(@RequestParam(required = false, defaultValue = "WEEK") String period, Model model) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();

        switch (period.toUpperCase()) {
            case "MONTH":
                startDate = endDate.minusMonths(1);
                break;
            case "YEAR":
                startDate = endDate.minusYears(1);
                break;
            case "WEEK":
            default:
                startDate = endDate.minusWeeks(1);
                break;
        }

        List<Transaction> transactions = transactionService.getTransactionsForPeriod(startDate, endDate);
        model.addAttribute("transactions", transactions);
        model.addAttribute("period", period); // Dodatkowy atrybut do przekazania wybranego okresu
        return "finance_reports";
    }

    @GetMapping("/add")
    public String addTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "finance_add";
    }

    @PostMapping("/add")
    public String saveTransaction(@ModelAttribute Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/finance";
    }

    @GetMapping("/reports/pdf")
    public ResponseEntity<byte[]> generatePdfReport(@RequestParam(required = false, defaultValue = "WEEK") String period) throws IOException {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();

        switch (period.toUpperCase()) {
            case "MONTH":
                startDate = endDate.minusMonths(1);
                break;
            case "YEAR":
                startDate = endDate.minusYears(1);
                break;
            case "WEEK":
            default:
                startDate = endDate.minusWeeks(1);
                break;
        }

        List<Transaction> transactions = transactionService.getTransactionsForPeriod(startDate, endDate);
        byte[] pdfReport = pdfReportService.generateTransactionReport(transactions);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=raport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport);
    }

}
