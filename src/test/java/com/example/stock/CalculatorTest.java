package com.example.stock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private SumaService sumaServiceMock;
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // Creamos el mock de SumaService
        sumaServiceMock = mock(SumaService.class);
        // Inyectamos el mock en Calculator
        calculator = new Calculator(sumaServiceMock);
    }

    @Test
    void testAdd_returnsCorrectSum() {
        // Configuramos el mock para que cuando se llame a suma(3, 2.5), retorne 5.5
        when(sumaServiceMock.suma(3, 2.5)).thenReturn(5.5);

        // Ejecutamos el método que queremos probar
        double result = calculator.add(3, 2.5);

        // Verificamos que el resultado sea el esperado
        assertEquals(5.5, result, "La suma debería ser 5.5");

        // Verificamos que el método suma haya sido llamado exactamente una vez con los parámetros 3 y 2.5
        verify(sumaServiceMock, times(1)).suma(3, 2.5);
    }
}

