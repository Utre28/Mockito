package com.example.stock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SecurityTests {

    private ProductService productService;

    @BeforeEach
    void setUp(){
        productService = new ProductService();
    }

    // Validación: No se permite nombre vacío
    @Test
    void testEmptyNameNotAllowed() {
        Product p = new Product("", 10, 1.5);
        assertFalse(productService.addProduct(p), "El nombre vacío no debe ser permitido");
    }

    // Validación: No se permite un nombre compuesto solo de espacios
    @Test
    void testNameWithSpacesNotAllowed() {
        Product p = new Product("    ", 10, 1.5);
        assertFalse(productService.addProduct(p), "Un nombre con solo espacios no debe ser permitido");
    }

    // Validación: No se permite cantidad negativa
    @Test
    void testNegativeQuantityNotAllowed() {
        Product p = new Product("Banana", -3, 1.0);
        assertFalse(productService.addProduct(p), "La cantidad negativa no debe ser permitida");
    }

    // Validación: No se permite precio negativo
    @Test
    void testNegativePriceNotAllowed() {
        Product p = new Product("Banana", 3, -0.5);
        assertFalse(productService.addProduct(p), "El precio negativo no debe ser permitido");
    }
}
