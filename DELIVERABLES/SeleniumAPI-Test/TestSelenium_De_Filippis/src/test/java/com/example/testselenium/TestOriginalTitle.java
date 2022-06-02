package com.example.testselenium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOriginalTitle {
    @Test
    public void testCompareValue(){
        assertTrue(OriginalTitle.compareValue());
    }
}


