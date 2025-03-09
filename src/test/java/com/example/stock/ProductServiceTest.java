package com.example.stock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {


    private SumaService sumaService;

    private ProductService productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // Usamos una lista real para ProductService (sin spy)
        List<Product> list = new ArrayList<>();
        sumaService = new SumaService();
        productService = new ProductService(list, sumaService);
    }

    // Caso exitoso: Producto válido se agrega correctamente
    @Test
    void whenTestAddProductValidProduct_shouldAddProduct() {

        Product producto1 = new Product("grafica",600,0.0);
        assertTrue(productService.addProduct(producto1));
        assertTrue(productService.getProducts().contains(producto1));


        System.out.println(productService.getProducts());






        /*
        Product p = new Product("Apple", 10, 1.5);
        boolean result = productService.addProduct(p);
        assertTrue(result, "El producto debería agregarse correctamente");
        // Verificamos el estado de la lista
        assertEquals(1, productService.getProducts().size(), "La lista de productos debe contener 1 elemento");
        assertTrue(productService.getProducts().contains(p), "La lista debe contener el producto agregado");
    */
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

    @Test
    void whenFindProductByName_shouldFindProduct() {
        Product p = new Product("Apple", 10, 1.5);
        productService.addProduct(p);
        Product productoEncontrado = productService.findProductByName(p.getName());
        assertEquals(p, productoEncontrado);
    }
    @Test
    void whenFindProductByNameButNotExist_shouldNotFindProduct() {
        Product p = new Product("Mac", 10, 1.5);
        Product p2 = new Product("Apple", 5, 10.5);
        productService.addProduct(p);
        productService.addProduct(p2);

        Product productNoEncontrado = productService.findProductByName("Pera");
        assertNull(productNoEncontrado);

    }
}
