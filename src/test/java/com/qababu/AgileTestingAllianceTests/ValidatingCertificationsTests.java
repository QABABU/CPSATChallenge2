package com.qababu.AgileTestingAllianceTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ValidatingCertificationsTests {

    private WebDriver driver;

    private SoftAssert softAssert;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        softAssert = new SoftAssert();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * This test validates certification menu, prints certifications count
     * And verifies the links working or not
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void validateCertificationsTest() throws IOException, InterruptedException {

        driver.get("https://agiletestingalliance.org/");

        WebElement certifications = driver.findElement(By.xpath("//a[contains(text(),'Certifications')]"));

        certifications.click();

        List<WebElement> certificationIcons = driver.findElements(By.xpath("//map//area[starts-with(@title, 'CP-')]"));

        System.out.println("Certification Icons: " + certificationIcons.size());

        List<String> urls = new ArrayList<String>();

        for (WebElement image : certificationIcons) {

            urls.add(image.getAttribute("href"));

            System.out.println(image.getAttribute("href"));
        }

        //verifying whether  links are working or not
        for (String url : urls) {
            int statusCode = given().get(url).getStatusCode();
            softAssert.assertEquals(statusCode, 200);
        }

        TakesScreenshot capture = (TakesScreenshot) driver;

        File screenshot = capture.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\certifications.png"));

        WebElement cp_cct = driver.findElement(By.xpath("//map//area[@alt='CP-CCT']"));

        Actions actions = new Actions(driver);
        //hovering on the cp_cct image
        actions.moveToElement(cp_cct).build().perform();

        Thread.sleep(2000);

        File cp_cct_tooltip = capture.getScreenshotAs(OutputType.FILE);
        //capturing cp_cct image
        FileUtils.copyFile(cp_cct_tooltip, new File(System.getProperty("user.dir") + "\\screenshots\\cp_cct_tooltip.png"));


    }

    @After
    public void closeBrowser() {

        driver.quit();
    }


}
