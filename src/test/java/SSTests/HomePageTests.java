package SSTests;

import SSBase.BaseClass;
import SSBase.DriverManager;
import SSPageObjects.SSHomePage;
import org.testng.annotations.Test;

public class HomePageTests extends BaseClass {



    @Test
    public void printMenFragranceAccessories() throws InterruptedException {
        System.out.println("I am a test");
        SSHomePage ssHomePage = new SSHomePage(DriverManager.getDriver());
        ssHomePage.navigateToMenFragrance();
    }
}
