package SSPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SSStoreFinderPage {

    private WebDriver driver;

    public SSStoreFinderPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



}
