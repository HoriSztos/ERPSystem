package com.example.erpsystem.finances;

import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testFindTransactionsBetweenDates() {
        // Przygotowanie danych testowych
        Transaction transaction1 = new Transaction();
        transaction1.setDate(LocalDate.of(2025, 1, 10));
        transaction1.setAmount(100.0);

        Transaction transaction2 = new Transaction();
        transaction2.setDate(LocalDate.of(2025, 1, 15));
        transaction2.setAmount(200.0);

        Transaction transaction3 = new Transaction();
        transaction3.setDate(LocalDate.of(2025, 2, 5));
        transaction3.setAmount(300.0);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);

        // Wywołanie metody repozytorium
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 31);
        List<Transaction> transactions = transactionRepository.findTransactionsBetweenDates(startDate, endDate);

        // Weryfikacje
        assertEquals(2, transactions.size());
        assertEquals(LocalDate.of(2025, 1, 10), transactions.get(0).getDate());
        assertEquals(LocalDate.of(2025, 1, 15), transactions.get(1).getDate());
    }

    @Test
    void testFindTop10Transactions() {
        // Przygotowanie danych testowych
        for (int i = 1; i <= 15; i++) {
            Transaction transaction = new Transaction();
            transaction.setDate(LocalDate.of(2025, 1, i));
            transaction.setAmount(i * 100.0);
            transactionRepository.save(transaction);
        }

        // Wywołanie metody repozytorium
        Page<Transaction> topTransactions = transactionRepository.findTop10Transactions(PageRequest.of(0, 10));

        // Weryfikacje
        assertEquals(10, topTransactions.getContent().size());
        assertEquals(LocalDate.of(2025, 1, 15), topTransactions.getContent().get(0).getDate()); // Najnowsza transakcja
        assertEquals(LocalDate.of(2025, 1, 6), topTransactions.getContent().get(9).getDate());  // 10-ta najnowsza transakcja
    }
}
