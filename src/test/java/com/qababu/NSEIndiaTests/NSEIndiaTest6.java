package com.qababu.NSEIndiaTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NSEIndiaTest6 {


    private WebDriver driver;
    private WebDriverWait wait;

    private FileOutputStream fos;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;

    @BeforeMethod
    public void setUp() throws FileNotFoundException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);

        File file = new File(".\\src\\main\\resources\\GainersLosersData.xlsx");

        fos = new FileOutputStream(file);

        wb = new XSSFWorkbook();
    }

    @Test()
    public void nseTestCase6() throws IOException {

        driver.get("https://www.nseindia.com/products.htm");

        WebElement liveMarketMenu = driver.findElement(By.xpath("//a[contains(text(),'Live Market')]"));

        Actions actions = new Actions(driver);

        Action action = actions.moveToElement(liveMarketMenu).build();

        action.perform();

        WebElement topTenGainersLosers = driver.findElement(By.xpath("//a[contains(text(),'Top Ten Gainers / Losers')]"));

        topTenGainersLosers.click();

        WebElement table = driver.findElement(By.id("topGainers"));

        int rowsCount = table.findElements(By.tagName("tr")).size();

        System.out.println("No of Rows in the table: "+rowsCount);

        int colsCount = table.findElements(By.tagName("th")).size();

        System.out.println("No of Columns in the table: "+colsCount);

        //writing the data from top 10 Gainers web table to excel sheet GainersData
        List<Double> gainersPercents = writeDataToExcelSheet("//table[@id='topGainers']", rowsCount, colsCount, "GainersData");

        //verifying  the high to low for Gainers
        verifyDescendingOrderOfList(gainersPercents, "For Gainers");

        //clicking on Losers tab
        driver.findElement(By.xpath("//li//a[text()='Losers' or @id='tab8']")).click();

        //writing the data from top 10 Losers web table to excel sheet LosersData
       List<Double> loserPercents = writeDataToExcelSheet("//table[@id='topLosers']", rowsCount, colsCount, "LosersData");

        //verifying  the high to low for Losers
        verifyDescendingOrderOfList(loserPercents, "For Losers");

        //we are comparing only Date,
        // if we compare time also, the system time and time on NSE Web will be different test will fail
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        Date date = new Date();
        String systemDate = formatter.format(date);

        String dateInApp = driver.findElement(By.id("dataTime")).getText();

        //Date validation
        if (dateInApp.contains(systemDate)){

            System.out.println("The market is live, and dates are equal");

        }else{

            System.out.println("May be market closed, and run this test when market is live");
        }
    }


    private List<Double> writeDataToExcelSheet(String tableXpath, int rCount, int cCount, String sheetName) {

        XSSFSheet sheet = wb.createSheet(sheetName);

        List<Double> changes = new ArrayList<Double>();

        for (int r = 0; r < rCount; r++) {

            XSSFRow newRow = sheet.createRow(r);

            for (int c = 0; c < cCount; c++) {

                if (r == 0) {

                    WebElement headerCells = driver.findElement(By.xpath(tableXpath+"/tbody//tr//th[" + (c + 1) + "]"));

                    String text = headerCells.getText();

                    XSSFCell newCell = newRow.createCell(c);

                    newCell.setCellType(CellType.STRING);

                    newCell.setCellValue(text);

                } else {

                    WebElement rowCells = driver
                            .findElement(By.xpath(tableXpath+"/tbody//tr[" + (r + 1) + "]" + "//td[" + (c + 1) + "]"));

                    String text = rowCells.getText();

                    if (c == 2) {

                        changes.add(Double.parseDouble((text.replace("-", ""))));
                    }

                    XSSFCell newCell = newRow.createCell(c);

                    newCell.setCellType(CellType.STRING);

                    newCell.setCellValue(text);
                }

            }

        }

        System.out.println("% Changes: " + changes);

        return changes;

    }

    private void verifyDescendingOrderOfList(List<Double> percents, String name){

        double maximum = percents.get(0);
        //lets say descending order is true, if the List is not in the set false
        boolean descending = true;

        for(int i=1; i<percents.size(); i++){

            if(maximum >= percents.get(i)){
                //max value changing to next value
                maximum = percents.get(i);
            }else {

                descending = false;
            }
        }

        if (descending){

            System.out.println("The percentage is high to low - "+name);
        }

    }



    @AfterMethod
    public void closeBrowser() throws IOException {

        fos.flush();

        wb.write(fos);

        fos.close();

        driver.quit();
    }
}
