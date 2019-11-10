package HelperClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CommonActions {

    private static WebDriverWait wait;

    private static Actions actions;

    public static void clickGivenElement(WebDriver driver, WebElement element){
        try{
            waitForElementVisibility(driver, element);
            element.click();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void waitForElementVisibility(WebDriver driver, WebElement element){
        try{
            wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void waitForElementInvisibility(WebDriver driver, WebElement element){
        try{
            wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.invisibilityOf(element));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void moveToGivenElement(WebDriver driver, WebElement target){
        try{
            waitForElementVisibility(driver, target);
            actions = new Actions(driver);
            actions.moveToElement(target).build().perform();
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void pause(long millis){

        try {
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<String> getAllDropdownOptions(WebDriver driver, WebElement dropdown){
        try{
            //waitForElementVisibility(driver, dropdown);
            Select select = new Select(dropdown);
            List<WebElement> optionElements = select.getOptions();
            List<String> optionValues = new ArrayList<String>();
            for (WebElement element : optionElements) {
                optionValues.add(element.getText().trim());
            }
            return optionValues;
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }


}
