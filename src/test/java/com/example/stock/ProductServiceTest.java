package com.example.stock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

    private ProductService productService;
    private List<Product> list;

    @BeforeEach
    void setUp() {
        // Usamos una lista real para ProductService (sin spy)
        list = new ArrayList<>();
        productService = new ProductService(list);
    }

    // Caso exitoso: Producto válido se agrega correctamente
    @Test
    void testAddProduct_ValidProduct() {
        Product p = new Product("Apple", 10, 1.5);
        boolean result = productService.addProduct(p);
        assertTrue(result, "El producto debería agregarse correctamente");
        // Verificamos el estado de la lista
        assertEquals(1, productService.getProducts().size(), "La lista de productos debe contener 1 elemento");
        assertTrue(productService.getProducts().contains(p), "La lista debe contener el producto agregado");
    }

    // Caso error: Producto con nombre vacío no se agrega
    @Test
    void testAddProduct_InvalidEmptyName() {
        Product p = new Product("   ", 10, 1.5);
        boolean result = productService.addProduct(p);
        assertFalse(result, "El producto con nombre vacío no debe agregarse");
        assertEquals(0, productService.getProducts().size(), "La lista de productos debe estar vacía");
    }

    // Caso error: Producto con cantidad negativa no se agrega
    @Test
    void testAddProduct_InvalidNegativeQuantity() {
        Product p = new Product("Apple", -5, 1.5);
        boolean result = productService.addProduct(p);
        assertFalse(result, "El producto con cantidad negativa no debe agregarse");
        assertEquals(0, productService.getProducts().size(), "La lista de productos debe estar vacía");
    }

    // Caso error: Producto con precio negativo no se agrega
    @Test
    void testAddProduct_InvalidNegativePrice() {
        Product p = new Product("Apple", 10, -1.0);
        boolean result = productService.addProduct(p);
        assertFalse(result, "El producto con precio negativo no debe agregarse");
        assertEquals(0, productService.getProducts().size(), "La lista de productos debe estar vacía");
    }
}
