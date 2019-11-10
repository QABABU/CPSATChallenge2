package SSPageObjects;

import HelperClasses.CommonActions;
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

    @FindBy(xpath = "//div[starts-with(@id,'slick-slide')]//div//a/img")
    private List<WebElement> bannerSliders;

    @FindBy(xpath = "//ul[contains(@class,'slick-dots')]/preceding-sibling::div[@class='dy-slick-arrow dy-next-arrow slick-arrow']")
    private WebElement sliderNextArrow;

    @FindBy(xpath = "//li[@class='slick-active']//button[@id='slick-slide-control00']")
    private WebElement firstSlickSlideButton;

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

        CommonActions.waitForElementVisibility(driver, cookiesCloseBtn);
        CommonActions.clickGivenElement(driver, cookiesCloseBtn);
        CommonActions.moveToGivenElement(driver, menMenuLink);
        CommonActions.moveToGivenElement(driver, menFragranceLink);
    }

    public void printMenFragranceAccessories(){
        try{
            for (WebElement element: fragranceAccessoriesLinks){
                System.out.println(element.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public SSStoreFinderPage navigateToStoreFinderPage(){
        CommonActions.waitForElementVisibility(driver, cookiesCloseBtn);
        CommonActions.clickGivenElement(driver, cookiesCloseBtn);
        CommonActions.clickGivenElement(driver, allStoriesLink);
        return new SSStoreFinderPage(driver);
    }

    public void clickSliderNextButton(){

        CommonActions.clickGivenElement(driver, sliderNextArrow);
    }



    public List<WebElement> getAllBanners(){

        return bannerSliders;
    }

    public void waitForInvisibilityOfFirstSliderButton(){

        CommonActions.waitForElementInvisibility(driver, firstSlickSlideButton);
    }

    public boolean isFirstSliderButtonDisplayed(){

        return firstSlickSlideButton.isDisplayed();
    }


}
