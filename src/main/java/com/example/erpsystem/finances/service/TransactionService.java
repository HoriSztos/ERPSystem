package com.example.erpsystem.finances.service;

import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.model.TransactionType;
import com.example.erpsystem.finances.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsForPeriod(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findTransactionsBetweenDates(startDate, endDate);
    }

    public Double calculateDailyProfit(LocalDate date) {
        List<Transaction> transactions = transactionRepository.findTransactionsBetweenDates(date, date);
        return transactions.stream()
                .mapToDouble(t -> t.getType() == TransactionType.INCOME ? t.getAmount() : -t.getAmount())
                .sum();
    }
}

