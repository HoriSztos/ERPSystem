package com.example.erpsystem.finances;

import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/finance")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
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
}
