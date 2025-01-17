package com.example.erpsystem.hr;

import com.example.erpsystem.hr.controller.HrController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HrControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HrController hrController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHrHome() {
        // Wywołanie metody
        String viewName = hrController.hrHome(model);

        // Weryfikacje
        assertEquals("hr", viewName);
        verifyNoInteractions(model);
    }
}

