package com.example.erpsystem.inventory;

import com.example.erpsystem.inventory.model.Product;
import com.example.erpsystem.inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InventoryControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInventory_NoFilters() {
        // Mock danych
        List<Product> products = Collections.emptyList();
        List<Product> expiringProducts = Collections.emptyList();
        List<Product> lowStockProducts = Collections.emptyList();

        when(productService.getAllProducts()).thenReturn(products);
        when(productService.getProductsExpiringSoon(7)).thenReturn(expiringProducts);
        when(productService.getProductsBelowMinimumStock()).thenReturn(lowStockProducts);

        // Wywołanie metody
        String viewName = inventoryController.getInventory(null, false, false, model);

        // Weryfikacje
        verify(productService).getAllProducts();
        verify(productService).getProductsExpiringSoon(7);
        verify(productService).getProductsBelowMinimumStock();
        verify(model).addAttribute("products", products);
        verify(model).addAttribute("expiringProducts", expiringProducts);
        verify(model).addAttribute("lowStockProducts", lowStockProducts);
        assertEquals("inventory", viewName);
    }

    @Test
    void testAddProductForm() {
        // Wywołanie metody
        String viewName = inventoryController.addProductForm(model);

        // Weryfikacje
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("product_add", viewName);
    }

    @Test
    void testAddProduct() {
        // Mock danych
        Product product = new Product();

        // Wywołanie metody
        String viewName = inventoryController.addProduct(product);

        // Weryfikacje
        verify(productService).saveProduct(product);
        assertEquals("redirect:/inventory", viewName);
    }

    @Test
    void testEditProductForm_ProductExists() {
        // Mock danych
        Long productId = 1L;
        Product product = new Product();

        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        // Wywołanie metody
        String viewName = inventoryController.editProductForm(productId, model);

        // Weryfikacje
        verify(productService).getProductById(productId);
        verify(model).addAttribute("product", product);
        assertEquals("product_edit", viewName);
    }

    @Test
    void testEditProductForm_ProductDoesNotExist() {
        // Mock danych
        Long productId = 1L;

        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        // Wywołanie metody
        String viewName = inventoryController.editProductForm(productId, model);

        // Weryfikacje
        verify(productService).getProductById(productId);
        verify(model).addAttribute("error", "Produkt o podanym ID nie istnieje.");
        assertEquals("redirect:/inventory", viewName);
    }

    @Test
    void testEditProduct() {
        // Mock danych
        Product product = new Product();

        // Wywołanie metody
        String viewName = inventoryController.editProduct(product);

        // Weryfikacje
        verify(productService).saveProduct(product);
        assertEquals("redirect:/inventory", viewName);
    }

    @Test
    void testDeleteProduct() {
        // Mock danych
        Long productId = 1L;

        // Wywołanie metody
        String viewName = inventoryController.deleteProduct(productId);

        // Weryfikacje
        verify(productService).deleteProduct(productId);
        assertEquals("redirect:/inventory", viewName);
    }
}

