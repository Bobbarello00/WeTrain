package com.seleniumapi.testseleniumapimanenti;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BitcoinValueNow {

    public static float returnBitcoinWorth() {
        System.setProperty("webdriver.chrome.driver","src/test/java/chrome_driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.it");
        driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]/div")).click(); //chiusura cookies
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("bitcoin valore euro"+ Keys.ENTER);
        String valueString = (driver.findElement(By.xpath("//*[@id=\"crypto-updatable_2\"]/div[3]/div[2]/span[1]")).getText()).replaceAll("\\.","");
        valueString = valueString.replaceAll(",",".");
        float bitcoinWorth = Float.parseFloat(valueString);
        driver.close();
        return bitcoinWorth;
    }
}
