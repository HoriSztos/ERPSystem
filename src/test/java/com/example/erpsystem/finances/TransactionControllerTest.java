package com.example.erpsystem.finances;

import com.example.erpsystem.dashboard.DashboardController;
import com.example.erpsystem.finances.TransactionController;
import com.example.erpsystem.finances.model.Transaction;
import com.example.erpsystem.finances.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Mock
    private Model model;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private DashboardController dashboardController;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testShowDashboard() {
        // Mock danych
        String username = "testUser";

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);

        // Wywołanie metody
        String viewName = dashboardController.showDashboard(model);

        // Weryfikacje
        verify(securityContext).getAuthentication();
        verify(authentication).getName();
        verify(model).addAttribute("username", username);
        assertEquals("dashboard", viewName);
    }

    @Test
    void testHome() {
        // Mock danych
        LocalDate today = LocalDate.now();
        double dailyProfit = 123.45;
        List<Transaction> recentTransactions = Collections.emptyList();

        when(transactionService.calculateDailyProfit(today)).thenReturn(dailyProfit);
        when(transactionService.getRecentTransactions()).thenReturn(recentTransactions);

        // Wywołanie metody
        String viewName = transactionController.home(model);

        // Weryfikacje
        verify(transactionService).calculateDailyProfit(today);
        verify(transactionService).getRecentTransactions();
        verify(model).addAttribute("dailyProfit", dailyProfit);
        verify(model).addAttribute("transactions", recentTransactions);
        assertEquals("finance_home", viewName);
    }

    @Test
    void testListTransactions() {
        // Mock danych
        List<Transaction> allTransactions = Collections.emptyList();

        when(transactionService.getAllTransactions()).thenReturn(allTransactions);

        // Wywołanie metody
        String viewName = transactionController.listTransactions(model);

        // Weryfikacje
        verify(transactionService).getAllTransactions();
        verify(model).addAttribute("transactions", allTransactions);
        assertEquals("finance_transactions", viewName);
    }

    @Test
    void testReports() {
        // Mock danych
        String period = "MONTH";
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        List<Transaction> transactionsForPeriod = Collections.emptyList();

        when(transactionService.getTransactionsForPeriod(startDate, endDate)).thenReturn(transactionsForPeriod);

        // Wywołanie metody
        String viewName = transactionController.reports(period, model);

        // Weryfikacje
        verify(transactionService).getTransactionsForPeriod(startDate, endDate);
        verify(model).addAttribute("transactions", transactionsForPeriod);
        verify(model).addAttribute("period", period);
        assertEquals("finance_reports", viewName);
    }

    @Test
    void testAddTransactionForm() {
        // Wywołanie metody
        String viewName = transactionController.addTransactionForm(model);

        // Weryfikacje
        verify(model).addAttribute(eq("transaction"), any(Transaction.class));
        assertEquals("finance_add", viewName);
    }

    @Test
    void testSaveTransaction() {
        // Mock danych
        Transaction transaction = new Transaction();

        // Wywołanie metody
        String viewName = transactionController.saveTransaction(transaction);

        // Weryfikacje
        verify(transactionService).saveTransaction(transaction);
        assertEquals("redirect:/finance", viewName);
    }
}
