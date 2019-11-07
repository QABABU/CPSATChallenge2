package com.qababu.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExcelDataReader {


    @DataProvider(name = "get-data")
    public static Object[][] getData(Method method) throws Exception {

        String[][] tabArray = new String[4][1];

        String testName = method.getName();

        String file_path = System.getProperty("user.dir")+"\\src\\main\\resources\\NSECompanies.xlsx";

        try {

            FileInputStream ExcelFile = new FileInputStream(file_path);

            XSSFWorkbook excelWBook = new XSSFWorkbook(ExcelFile);

            XSSFSheet excelWSheet = excelWBook.getSheet("companies");

            int totalRows = excelWSheet.getPhysicalNumberOfRows();

            System.out.println("Total rows: "+totalRows);

            for(int i=0; i < totalRows; i++){

                XSSFRow row = excelWSheet.getRow(i);

                if(row != null){

                    XSSFCell cell = row.getCell(0);

                    if(cell !=null){
                        System.out.println("company name: "+cell.getStringCellValue());
                        tabArray[i][0] = cell.getStringCellValue();
                    }

                }
            }


        }catch (FileNotFoundException e){

            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();

        }catch (IOException e){

            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        return(tabArray);
    }

    /*private static String getCellData(int RowNum, int ColNum) throws Exception {

        try{
            XSSFCell cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            return cell.getStringCellValue();

            }catch (Exception e){

                System.out.println(e.getMessage());

                throw (e);

            }

        }*/
}
