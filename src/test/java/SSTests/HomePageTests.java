package SSTests;

import SSBase.BaseClass;
import SSBase.DriverManager;
import SSPageObjects.SSHomePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageTests extends BaseClass {

    private SSHomePage ssHomePage;

    @BeforeMethod
    public void setUp(Method method){
        System.out.println("Executing the test - "+method.getName());
        driverInitialization();
        ssHomePage = new SSHomePage(DriverManager.getDriver());
    }

    /**
     * This test clicking on all the banners
     */
    @Test
    public void clickingBannerSlidersTest(){
        List<WebElement> banners = ssHomePage.getAllBanners();
        int bannersCount = banners.size();
        String firstBannerTitle = banners.get(0).getAttribute("title");
        System.out.println("First Banner Title: "+firstBannerTitle);
        //clicking the banners one by one
        for (int i=0; i<bannersCount; i++){

            ssHomePage.clickSliderNextButton();
        }
    }

    /**
     * This test prints all the accessories under men's fragrance
     */
    @Test
    public void printMenFragranceAccessoriesTest() throws InterruptedException {
        ssHomePage = new SSHomePage(DriverManager.getDriver());
        ssHomePage.navigateToMenFragrance();
        ssHomePage.printMenFragranceAccessories();
    }
}
