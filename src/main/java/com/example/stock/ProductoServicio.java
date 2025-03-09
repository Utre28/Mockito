package com.example.stock;

import java.util.ArrayList;
import java.util.List;

// Clase que gestiona productos en una tienda
class ProductoServicio {
    private List<Producto> productos = new ArrayList<>();

    public boolean agregarProducto(Producto p) {
        // Validar que el id sea mayor a 0, que nombre no este vacio y que precio no sea menor a 0

        if (p.getId() <= 0 || p.getNombre().trim().isEmpty() || p.getPrecio() <= 0 || existeEnLaLista(p)) {
            return false;
        }
        // Si pasa las validaciones, se agrega el producto
        productos.add(p);
        return true;
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }

    private boolean existeEnLaLista(Producto p) {
        for (Producto prod : productos) {
            if (prod.getId() == p.getId()) {
                return true;
            }
        }return false;
    }

}


