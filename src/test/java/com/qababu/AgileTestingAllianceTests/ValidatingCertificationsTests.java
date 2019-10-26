package com.qababu.AgileTestingAllianceTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ValidatingCertificationsTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void validateCertificationsTest(){

        driver.get("https://agiletestingalliance.org/");

        WebElement certifications = driver.findElement(By.xpath("//a[contains(text(),'Certifications')]"));

        certifications.click();

        List<WebElement> certificationIcons = driver.findElements(By.xpath("//map//area[starts-with(@title, 'CP-')]"));

        System.out.println("Certification Icons: "+certificationIcons.size());

        for (WebElement image : certificationIcons) {

            System.out.println(image.getAttribute("href"));
        }
    }


}
