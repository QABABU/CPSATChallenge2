package SSPageObjects;

import HelperClasses.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SSStoreFinderPage {

    private WebDriver driver;

    public SSStoreFinderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "cityName")
    private WebElement selectCityDropdown;

    public List<String> getAllCities(){

        return CommonActions.getAllDropdownOptions(driver,selectCityDropdown);
    }



}
