package com.qababu.NSEIndiaTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NSEIndiaTest2 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void printMarketWatchDetailsTest() {

        driver.get("https://www.nseindia.com/");
        int minimum = -1;
        int index = -1;
        List<WebElement> categories = driver.findElements(By.xpath("//ul[@class='advanceTab']/li/p"));
        List<WebElement> numbers = driver.findElements(By.xpath("//ul[@class='advanceTab']/li/span"));
        String firstValue = numbers.get(0).getText();
        minimum = Integer.parseInt(firstValue);
        for (int i = 0; i < numbers.size(); i++) {
            int val = Integer.parseInt(numbers.get(i).getText());
            if (minimum > val) {
                minimum = val;
                index = i;
            }
        }
        System.out.println(categories.get(index).getText() + " " + minimum);
    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
