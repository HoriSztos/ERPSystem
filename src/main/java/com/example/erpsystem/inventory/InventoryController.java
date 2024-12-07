package com.example.erpsystem.inventory;

import com.example.erpsystem.inventory.repository.ProductRepository;
import com.example.erpsystem.inventory.service.ProductService;
import com.example.erpsystem.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventory") // This maps all requests to /inventory
public class InventoryController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getInventory(Model model) {
        // Pobranie listy wszystkich produktów
        List<Product> products = productService.getAllProducts();

        // Pobranie produktów z krótkim terminem ważności (7 dni)
        List<Product> expiringProducts = productService.getProductsExpiringSoon(7);

        model.addAttribute("products", products);
        model.addAttribute("expiringProducts", expiringProducts);
        return "inventory";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/inventory";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product_edit";
        } else {
            // Obsługa błędu: produkt nie istnieje
            model.addAttribute("error", "Produkt o podanym ID nie istnieje.");
            return "redirect:/inventory";
        }
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/inventory";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/inventory";
    }
}