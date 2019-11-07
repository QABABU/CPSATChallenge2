package SSPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SSHomePage {

    private WebDriver driver;

    public SSHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title='MEN']")
    private WebElement menMenuLink;

    @FindBy(xpath = "//ul[contains(@class,'lvl1')]/li[4]/div[1]/div[1]/ul[1]/li[6]/a[1]")
    private WebElement menFragranceLink;

    @FindBy(xpath = "//a[contains(text(),'Men') and contains(text(), 'Fragrance')]/following-sibling::div[@class='lvl3']//ul//li//span//a")
    private List<WebElement> fragranceAccessoriesLinks;

    @FindBy(xpath = "//div[@id='firstVisit']/div")
    private WebElement cookiesCloseBtn;

    @FindBy(xpath = "//ul[contains(@class,'text-right')]//a[contains(text(),'All Stores')]")
    private WebElement allStoriesLink;

    public void navigateToMenFragrance() throws InterruptedException {

        Actions actions = new Actions(driver);

        Thread.sleep(3000);

        if(cookiesCloseBtn.isDisplayed()){
            cookiesCloseBtn.click();
        }

        actions.moveToElement(menMenuLink).build().perform();

        Thread.sleep(2000);

        actions.moveToElement(menFragranceLink).build().perform();

        Thread.sleep(2000);

        for (WebElement element: fragranceAccessoriesLinks){

            System.out.println(element.getText());
        }
    }


    public SSStoreFinderPage navigateToStoreFinderPage(){

        allStoriesLink.click();
        return new SSStoreFinderPage(driver);
    }


}
