package SSTests;

import SSBase.BaseClass;
import SSBase.DriverManager;
import SSPageObjects.SSHomePage;
import SSPageObjects.SSStoreFinderPage;
import org.testng.annotations.Test;

import java.util.List;

public class StoreFinderPageTests extends BaseClass {

    @Test
    public void printAllStores(){
        System.out.println("Executing - printAllStores");
        SSHomePage ssHomePage = new SSHomePage(DriverManager.getDriver());
        SSStoreFinderPage ssStoreFinderPage = ssHomePage.navigateToStoreFinderPage();
        List<String> options = ssStoreFinderPage.getAllCities();
        System.out.println("Shoppers Stop - all available cities");
        for (String option : options) {
            System.out.println(option);
        }

    }
}
