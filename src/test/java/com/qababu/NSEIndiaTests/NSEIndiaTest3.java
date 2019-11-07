package com.qababu.NSEIndiaTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NSEIndiaTest3 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void printMarketWatchDetailsTest() throws IOException {

        driver.get("https://www.nseindia.com/");
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='keyword']"));
        wait.until(ExpectedConditions.visibilityOf(searchBox));

        //searching for eicher motor
        searchBox.sendKeys("Eicher Motor");
        WebElement element = driver.findElement(By.xpath("//*[@id='ajax_response']/ol/li/a"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        WebElement companyName = driver.findElement(By.id("companyName"));
        wait.until(ExpectedConditions.visibilityOf(companyName));

        //Taking the screenshot
        TakesScreenshot capture = (TakesScreenshot)driver;
        File screenshot =  capture.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\screenshots\\eicher_motor.png"));

        //printing face value, 52 week high, 52 week low
        String faceValue = driver.findElement(By.id("faceValue")).getText();
        String high52 = driver.findElement(By.id("high52")).getText();
        String low52 = driver.findElement(By.id("low52")).getText();
        System.out.println("Face Value "+faceValue);
        System.out.println("52 week high "+high52);
        System.out.println("52 week low "+low52);
    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
