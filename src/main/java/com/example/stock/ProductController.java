package com.example.stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductController {

    @FXML
    public TableView<Product> productTable;

    @FXML
    public TableColumn<Product, String> nameColumn;

    @FXML
    public TableColumn<Product, Integer> quantityColumn;

    @FXML
    public TableColumn<Product, Double> priceColumn;

    private ProductService productService = new ProductService();

    // Utilizamos un ObservableList para tener una lista "viva" para la tabla
    private ObservableList<Product> observableProducts;

    @FXML
    public void initialize() {
        // Configuramos las columnas con sus propiedades
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Creamos el ObservableList a partir de la lista del servicio
        observableProducts = FXCollections.observableArrayList(productService.getProducts());
        productTable.setItems(observableProducts);
    }

    @FXML
    public void addProduct() {
        // Solicitamos los datos mediante diálogos
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Add Product");
        nameDialog.setHeaderText("Enter product name:");
        String name = nameDialog.showAndWait().orElse("");

        TextInputDialog quantityDialog = new TextInputDialog();
        quantityDialog.setTitle("Add Product");
        quantityDialog.setHeaderText("Enter product quantity:");
        int quantity;
        try {
            quantity = Integer.parseInt(quantityDialog.showAndWait().orElse("0"));
        } catch(NumberFormatException e) {
            showError("Invalid quantity");
            return;
        }

        TextInputDialog priceDialog = new TextInputDialog();
        priceDialog.setTitle("Add Product");
        priceDialog.setHeaderText("Enter product price:");
        double price;
        try {
            price = Double.parseDouble(priceDialog.showAndWait().orElse("0"));
        } catch(NumberFormatException e) {
            showError("Invalid price");
            return;
        }

        Product newProduct = new Product(name, quantity, price);
        if(productService.addProduct(newProduct)) {
            observableProducts.add(newProduct);
            showInfo("Product added successfully");
        } else {
            showError("Error: Invalid product data");
        }
    }

    @FXML
    public void modifyProduct() {
        // Solicita el nombre del producto a modificar
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Modify Product");
        nameDialog.setHeaderText("Enter the product name to modify:");
        String name = nameDialog.showAndWait().orElse("");
        Product p = productService.findProductByName(name);
        if(p == null) {
            showError("Product not found");
            return;
        }

        // Solicita los nuevos datos, mostrando los valores actuales
        TextInputDialog quantityDialog = new TextInputDialog(String.valueOf(p.getQuantity()));
        quantityDialog.setTitle("Modify Product");
        quantityDialog.setHeaderText("Enter new quantity:");
        int quantity;
        try {
            quantity = Integer.parseInt(quantityDialog.showAndWait().orElse(String.valueOf(p.getQuantity())));
        } catch(NumberFormatException e) {
            showError("Invalid quantity");
            return;
        }

        TextInputDialog priceDialog = new TextInputDialog(String.valueOf(p.getPrice()));
        priceDialog.setTitle("Modify Product");
        priceDialog.setHeaderText("Enter new price:");
        double price;
        try {
            price = Double.parseDouble(priceDialog.showAndWait().orElse(String.valueOf(p.getPrice())));
        } catch(NumberFormatException e) {
            showError("Invalid price");
            return;
        }

        p.setQuantity(quantity);
        p.setPrice(price);
        productTable.refresh();
        showInfo("Product modified successfully");
    }

    @FXML
    public void deleteProduct() {
        // Solicita el nombre del producto a eliminar
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Delete Product");
        nameDialog.setHeaderText("Enter the product name to delete:");
        String name = nameDialog.showAndWait().orElse("");
        if(productService.deleteProduct(name)) {
            observableProducts.removeIf(p -> p.getName().equalsIgnoreCase(name));
            showInfo("Product deleted successfully");
        } else {
            showError("Product not found");
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
