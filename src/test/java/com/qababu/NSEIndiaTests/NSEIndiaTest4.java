package com.qababu.NSEIndiaTests;

import com.qababu.utils.ExcelDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NSEIndiaTest4 {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    @Test(dataProvider = "get-data", dataProviderClass = ExcelDataReader.class)
    public void nseTestCase4(String companyTitle) throws IOException {

        driver.get("https://www.nseindia.com/");
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='keyword']"));
        wait.until(ExpectedConditions.visibilityOf(searchBox));

        //searching for given company title
        searchBox.clear();
        searchBox.sendKeys(companyTitle);
        WebElement element = driver.findElement(By.xpath("//*[@id='ajax_response']/ol/li/a"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        WebElement companyName = driver.findElement(By.id("companyName"));
        wait.until(ExpectedConditions.visibilityOf(companyName));

        //Taking the screenshot
        TakesScreenshot capture = (TakesScreenshot)driver;
        File screenshot =  capture.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"\\screenshots\\"+companyTitle+".png"));

        //printing face value, 52 week high, 52 week low
        String faceValue = driver.findElement(By.id("faceValue")).getText();
        String high52 = driver.findElement(By.id("high52")).getText();
        String low52 = driver.findElement(By.id("low52")).getText();
        System.out.println("Face Value "+faceValue);
        System.out.println("52 week high "+high52);
        System.out.println("52 week low "+low52);
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
