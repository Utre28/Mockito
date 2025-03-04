package com.example.stock;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private List<Product> products;

    // Constructor por defecto
    public ProductService() {
        this.products = new ArrayList<>();
    }

    // Constructor para inyectar una lista (útil para tests)
    public ProductService(List<Product> products) {
        this.products = products;
    }

    /**
     * Agrega un producto tras validar que el nombre no esté vacío
     * y que la cantidad y el precio sean no negativos.
     * @return true si se añade correctamente, false en caso contrario.
     */
    public boolean addProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty() ||
                product.getQuantity() < 0 || product.getPrice() < 0) {
            return false;
        }
        products.add(product);
        return true;
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * Busca un producto por nombre (ignorando mayúsculas/minúsculas).
     */
    public Product findProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Elimina un producto por nombre.
     * @return true si se eliminó, false en caso contrario.
     */
    public boolean deleteProduct(String name) {
        Product p = findProductByName(name);
        if (p != null) {
            products.remove(p);
            return true;
        }
        return false;
    }
}
