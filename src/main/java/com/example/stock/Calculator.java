package com.example.stock;


public class Calculator {
    private SumaService sumaService;

    public Calculator(SumaService sumaService) {
        this.sumaService = sumaService;
    }

    public double add(int a, double b) {
        // Se delega la operación de suma al SumaService
        return sumaService.suma(a, b);
    }
}

