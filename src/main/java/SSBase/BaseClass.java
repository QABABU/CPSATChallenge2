package SSBase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {


    private static String appUrl;

    @BeforeClass
    public void setUp() throws IOException {

        Properties prop = System.getProperties();

        prop.load(new FileInputStream(".\\src\\main\\resources\\SSConfig.properties"));

        appUrl = prop.getProperty("appUrl");

        System.out.println("Application URL: "+appUrl);

        String browserType = prop.getProperty("browser");

        System.out.println("The preferred browser type is: "+browserType);

        DriverManager.setBrowserType(browserType);

    }


    /**
     * Browser launch and application navigation
     */
    protected void driverInitialization(){

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        DriverManager.getDriver().get(appUrl);
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        DriverManager.getDriver().manage().deleteAllCookies();
    }



   @AfterMethod
    public static void driverClose(){

        DriverManager.quitDriver();
    }


}
