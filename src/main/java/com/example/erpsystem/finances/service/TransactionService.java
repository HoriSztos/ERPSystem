package com.example.erpsystem.finances.service;

import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.model.TransactionType;
import com.example.erpsystem.finances.repository.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Transaction> getRecentTransactions() {
        Pageable pageable = PageRequest.of(0, 10);
        return transactionRepository.findTop10Transactions(pageable).getContent();
    }

    public Double calculateDailyProfit(LocalDate date) {
        List<Transaction> transactions = transactionRepository.findTransactionsBetweenDates(date, date);
        return transactions.stream()
                .mapToDouble(t -> t.getType() == TransactionType.przychod ? t.getAmount() : -t.getAmount())
                .sum();
    }
}

