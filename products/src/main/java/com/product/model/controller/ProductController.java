package com.product.model.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private static final String FILE_PATH = "products.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private synchronized List<Product> loadProducts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private synchronized void saveProducts(List<Product> products) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addProduct";
        }
        
        System.out.println("Saved Product Details: " + product.toString());
        
        List<Product> products = loadProducts();
        // Remove existing product with same ID if present, then add
        products.removeIf(p -> p.getProductId().equals(product.getProductId()));
        products.add(product);
        saveProducts(products);

        model.addAttribute("successMessage", "Product added successfully!");
        return "addProduct";
    }

    @GetMapping("/viewProduct")
    public String viewProductPage(Model model, @RequestParam(value = "selectedProductId", required = false) String selectedProductId) {
        List<Product> products = loadProducts();
        model.addAttribute("products", products);
        
        Product selectedProduct = new Product();
        if (selectedProductId != null && !selectedProductId.isEmpty()) {
            Optional<Product> p = products.stream()
                    .filter(prod -> prod.getProductId().equals(selectedProductId))
                    .findFirst();
            if (p.isPresent()) {
                selectedProduct = p.get();
                model.addAttribute("selectedProductId", selectedProductId);
            }
        }
        model.addAttribute("selectedProduct", selectedProduct);
        return "viewProduct";
    }

    @PostMapping("/actionProduct")
    public String actionProduct(@ModelAttribute("selectedProduct") Product product, 
                                @RequestParam String action, 
                                @RequestParam(value = "selectedProductId", required = false) String selectedProductId,
                                Model model) {
                                    
        if ("View".equals(action)) {
            return "redirect:/viewProduct?selectedProductId=" + selectedProductId;
        } else if ("Edit".equals(action)) {
            model.addAttribute("editMode", true);
            List<Product> products = loadProducts();
            model.addAttribute("products", products);
            model.addAttribute("selectedProductId", selectedProductId);
            return "viewProduct";
        } else if ("Delete".equals(action)) {
            if (selectedProductId != null && !selectedProductId.isEmpty()) {
                List<Product> products = loadProducts();
                products.removeIf(prod -> prod.getProductId().equals(selectedProductId));
                saveProducts(products);
            }
            return "redirect:/viewProduct";
        } else if ("Submit".equals(action)) {
            List<Product> products = loadProducts();
            products.removeIf(p -> p.getProductId().equals(product.getProductId()));
            products.add(product);
            saveProducts(products);
            return "redirect:/viewProduct?selectedProductId=" + product.getProductId();
        }
        
        return "redirect:/viewProduct";
    }
}
