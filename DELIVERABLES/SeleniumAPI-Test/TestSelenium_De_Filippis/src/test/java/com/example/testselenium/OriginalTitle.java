package com.example.testselenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;


public class OriginalTitle {
    public static boolean compareValue(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/example/testselenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://it.wikipedia.org/wiki/Tenebre_e_ossa");
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[13]/td/i")).click();
        WebElement citation = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[2]/tbody/tr[2]/td/span"));
        String value = citation.getText();
        driver.close();
        return Objects.equals(value, "Siege and Storm");
    }
}
