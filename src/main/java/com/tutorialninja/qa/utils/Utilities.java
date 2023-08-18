package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.github.javafaker.Faker;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME=20;
	public static final int PAGE_LOAD_TIME=20;

	public static String generateEmailWithTimeStamp()
	{
		Date date=new Date();
		String timestamp=date.toString().replace(":", "_").replace(" ", "_");
		return "rajesh"+timestamp+"@gmail.com";

	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

		File excelFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");

		FileInputStream fisExcel;
		XSSFWorkbook workbook=null;
		try {
			fisExcel = new FileInputStream(excelFile);
			workbook=new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();

		Object [][] data=new Object[rows][cols];

		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);

			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch(cellType) {

				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();

				}
			}
		}
		return data;
	}
	
	public static String generateRandomPassword()
	{
		Faker faker=new Faker();
		return(faker.internet().password());
	}
	
	public static String generateRandomFirstName()
	{
		Faker faker=new Faker();
		return(faker.name().firstName());
	}
	
	public static String generateRandomLasName()
	{
		Faker faker=new Faker();
		return(faker.name().lastName());
	}
	
	public static String captureScreenShot(WebDriver driver, String testName) {
		
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}

}
