package com.example.stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoServicioTest {

    private ProductoServicio productoServicio;

    @BeforeEach
    public void setUp() {
        productoServicio = new ProductoServicio();
    }

    @Test
    void cuandoProductoValido_deberiaAgregarProducto() {
        Producto p = new Producto(1, "pera", 1);
        assertTrue(productoServicio.agregarProducto(p), "El producto válido debe ser agregado");
        assertTrue(productoServicio.obtenerTodosLosProductos().contains(p));
    }

    @Test
    void cuandoProductoConIdInvalido_noSeAgregaProducto() {
        Producto p = new Producto(-1, "pera", 1);
        boolean resultado = productoServicio.agregarProducto(p);
        assertFalse(resultado, "El producto no se debe agregar porque el ID es inválido");
    }

    @Test
    void cuandoProductoConIdYaExistente_noSeAgregaProducto() {
        Producto p1 = new Producto(1, "pera", 1);
        Producto p2 = new Producto(1, "manzana", 1);
        productoServicio.agregarProducto(p1);
        boolean resultado = productoServicio.agregarProducto(p2);
        assertFalse(resultado, "El producto no se debe agregar porque el ID ya existe en la lista");
    }

    @Test
    void cuandoProductoConNombreInvalido_noSeAgregaProducto() {
        Producto p = new Producto(1, " ", 1);
        boolean resultado = productoServicio.agregarProducto(p);
        assertFalse(resultado, "El producto no se debe agregar porque el nombre es inválido");
    }

    @Test
    void cuandoProductoConPrecioInvalido_noSeAgregaProducto() {
        Producto p = new Producto(1, "pera", -1);
        boolean resultado = productoServicio.agregarProducto(p);
        assertFalse(resultado, "El producto no se debe agregar porque el precio es inválido");
    }

    @Test
    void cuandoBuscarProductoPorId_existente_retornaProducto() {
        Producto p = new Producto(1, "pera", 1);
        productoServicio.agregarProducto(p);
        Producto productoEncontrado = productoServicio.buscarProductoPorId(p.getId());
        assertEquals(p, productoEncontrado, "Se debe encontrar el producto agregado");
    }

    @Test
    void cuandoBuscarProductoPorId_noExistente_retornaNull() {
        Producto p1 = new Producto(1, "pera", 1);
        Producto p2 = new Producto(2, "manzana", 1);
        productoServicio.agregarProducto(p1);
        productoServicio.agregarProducto(p2);
        Producto productoEncontrado = productoServicio.buscarProductoPorId(3);
        assertNull(productoEncontrado, "No se debe encontrar un producto con un ID no existente");
    }
}
