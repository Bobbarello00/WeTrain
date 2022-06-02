package com.seleniumapi.testseleniumapimanenti;

import com.seleniumapi.testseleniumapimanenti.BitcoinValueNow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBitcoinValueNow {
    /*
     * il test verifica che il valore di
     * bitcoin non sia sceso sotto i 10000 euro.
     */
    @Test void testBitcoinValue() {
        float value = BitcoinValueNow.returnBitcoinWorth();
        assertTrue(value > 10000);
    }
}
