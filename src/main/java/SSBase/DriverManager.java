package SSBase;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

    private static String browserType;

    static void setBrowserType(String browser){

        browserType = browser;

    }

    public static WebDriver getDriver(){

        WebDriver driver = DriverManager.threadDriver.get();

        if(driver == null){
            if ("Chrome".equals(browserType)) {
                System.out.println("Launching Chrome Browser");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                threadDriver.set(driver);
            } else if ("Firefox".equals(browserType)) {
                System.out.println("Launching Firefox Browser");
                WebDriverManager.firefoxdriver().arch32().setup();
                driver = new FirefoxDriver();
            } else if ("IE".equals(browserType)) {
                System.out.println("Launching IE Browser");
                WebDriverManager.iedriver().arch32().setup();
                driver = new InternetExplorerDriver();
            }
        }

        return driver;
    }

    public static void quitDriver(){
        DriverManager.getDriver().quit();
        DriverManager.threadDriver.set(null);
    }

}
