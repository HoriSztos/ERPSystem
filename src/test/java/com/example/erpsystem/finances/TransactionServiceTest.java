package com.example.erpsystem.finances;

import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.model.TransactionType;
import com.example.erpsystem.finances.repository.TransactionRepository;
import com.example.erpsystem.finances.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        // Mock danych
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactions);

        // Wywołanie metody
        List<Transaction> result = transactionService.getAllTransactions();

        // Weryfikacje
        verify(transactionRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testSaveTransaction() {
        // Mock danych
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Wywołanie metody
        Transaction result = transactionService.saveTransaction(transaction);

        // Weryfikacje
        verify(transactionRepository).save(transaction);
        assertEquals(100.0, result.getAmount());
    }

    @Test
    void testGetTransactionsForPeriod() {
        // Mock danych
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 31);
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());

        when(transactionRepository.findTransactionsBetweenDates(startDate, endDate)).thenReturn(transactions);

        // Wywołanie metody
        List<Transaction> result = transactionService.getTransactionsForPeriod(startDate, endDate);

        // Weryfikacje
        verify(transactionRepository).findTransactionsBetweenDates(startDate, endDate);
        assertEquals(2, result.size());
    }

    @Test
    void testGetRecentTransactions() {
        // Mock danych
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction(), new Transaction());
        Page<Transaction> page = new PageImpl<>(transactions);

        when(transactionRepository.findTop10Transactions(PageRequest.of(0, 10))).thenReturn(page);

        // Wywołanie metody
        List<Transaction> result = transactionService.getRecentTransactions();

        // Weryfikacje
        verify(transactionRepository).findTop10Transactions(PageRequest.of(0, 10));
        assertEquals(3, result.size());
    }

    @Test
    void testCalculateDailyProfit() {
        // Mock danych
        LocalDate date = LocalDate.of(2025, 1, 10);
        Transaction t1 = new Transaction();
        t1.setType(TransactionType.przychod);
        t1.setAmount(200.0);

        Transaction t2 = new Transaction();
        t2.setType(TransactionType.wydatek);
        t2.setAmount(50.0);

        List<Transaction> transactions = Arrays.asList(t1, t2);
        when(transactionRepository.findTransactionsBetweenDates(date, date)).thenReturn(transactions);

        // Wywołanie metody
        Double profit = transactionService.calculateDailyProfit(date);

        // Weryfikacje
        verify(transactionRepository).findTransactionsBetweenDates(date, date);
        assertEquals(150.0, profit);
    }

    @Test
    void testCalculateDailyProfit_NoTransactions() {
        // Mock danych
        LocalDate date = LocalDate.of(2025, 1, 10);

        when(transactionRepository.findTransactionsBetweenDates(date, date)).thenReturn(Collections.emptyList());

        // Wywołanie metody
        Double profit = transactionService.calculateDailyProfit(date);

        // Weryfikacje
        verify(transactionRepository).findTransactionsBetweenDates(date, date);
        assertEquals(0.0, profit);
    }
}
