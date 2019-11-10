package com.qababu.AgileTestingAllianceTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

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
     * Captures screenshot of CP_CCT with tooltip
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void validateCertificationsTest() throws IOException, InterruptedException, AWTException, FindFailed {

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

        //verifying whether  links are working or not using Rest-assured
        for (String url : urls) {
            int statusCode = given().get(url).getStatusCode();
            softAssert.assertEquals(statusCode, 200);
        }

        TakesScreenshot capture = (TakesScreenshot) driver;

        File screenshot = capture.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\certifications.png"));

        WebElement cp_cct = driver.findElement(By.xpath("//map//area[@alt='CP-CCT']"));

        //Using Sikuli to capture CP_CCT tooltip
        moveToElementUsingSikuli();
        File cp_cct_tooltip = capture.getScreenshotAs(OutputType.FILE);

        //capturing cp_cct image
        FileUtils.copyFile(cp_cct_tooltip, new File(System.getProperty("user.dir") + "\\screenshots\\cp_cct_tooltip.png"));


        //Actions, Robot classes and JavaScriptExecutor didn't work finally Sikuli we helped me complete this task
          /*int x = cp_cct.getLocation().getX();
        int y = cp_cct.getLocation().getY();
        Robot robot = new Robot();
        robot.mouseMove(x, y);
        Thread.sleep(3000);*/

       /* String strJavaScript = "var element = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );"
                + "element.dispatchEvent(mouseEventObj);";

        ((JavascriptExecutor) driver).executeScript(strJavaScript, cp_cct);*/

       /* Actions actions = new Actions(driver);
        //hovering on the cp_cct image
        actions.moveToElement(cp_cct).build().perform();
       */

    }


    /**
     * Mouseover on the CP_CCT image and capture the screenshot with tooltip
     * @throws FindFailed
     * @throws InterruptedException
     */
    private void moveToElementUsingSikuli() throws FindFailed, InterruptedException {

        Screen screen = new Screen();
        Pattern cp_cct_img = new Pattern( System.getProperty("user.dir") + "\\screenshots\\cpcct_refrence_img.PNG");
        screen.wait(cp_cct_img, 20);
        screen.hover();
        Thread.sleep(2000);
        //capturing the screenshot after mouseover
        screen.saveScreenCapture(System.getProperty("user.dir") + "\\screenshots\\", "cp_cct_tooltip");
    }

    @After
    public void closeBrowser() {

        driver.quit();
    }


}
