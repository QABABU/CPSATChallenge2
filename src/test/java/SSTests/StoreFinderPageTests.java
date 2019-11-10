package SSTests;

import SSBase.BaseClass;
import SSBase.DriverManager;
import SSPageObjects.SSHomePage;
import SSPageObjects.SSStoreFinderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.List;

public class StoreFinderPageTests extends BaseClass {

    private SSHomePage ssHomePage;
    private SSStoreFinderPage ssStoreFinderPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp(Method method){
        System.out.println("Executing the test - "+method.getName());
        driverInitialization();
        ssHomePage = new SSHomePage(DriverManager.getDriver());
        softAssert = new SoftAssert();
    }


    /**
     * This test prints all the available stores
     * Verifying Agra, Bhopal and Mysore cities present in the list
     * @param city1
     * @param city2
     * @param city3
     */
    @Test(dataProvider = "getData")
    public void printAllStores(String city1, String city2, String city3){
        System.out.println("Executing - printAllStores");
        SSHomePage ssHomePage = new SSHomePage(DriverManager.getDriver());
        ssStoreFinderPage = ssHomePage.navigateToStoreFinderPage();
        List<String> options = ssStoreFinderPage.getAllCities();
        System.out.println("Shoppers Stop - all available cities");
        // print all cities
        for (String option : options) {
            System.out.println(option);
        }
        // verifying Agra, Bhopal and Mysore in the cities
        softAssert.assertTrue(options.contains(city1));
        softAssert.assertTrue(options.contains(city2));
        softAssert.assertTrue(options.contains(city3));
        softAssert.assertAll("Agra, Bhopal and Mysore cities are not present in Dropdown");
        System.out.println("Title of the page: "+DriverManager.getDriver().getTitle());
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{"Agra", "Bhopal", "Mysore"}};
    }
}
