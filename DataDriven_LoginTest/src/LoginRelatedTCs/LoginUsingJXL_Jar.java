package LoginRelatedTCs;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginUsingJXL_Jar {
	WebDriver driver;
	
	Object[][] Data=null;
	
	@DataProvider(name="LoginData")
	public Object[][] TestData() throws BiffException, IOException {
		Data=GetExcelData();
		return Data;
	}
	
	public Object[][] GetExcelData() throws BiffException, IOException {
		FileInputStream Excel=new FileInputStream("C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\DataDriven\\JXL\\Data.xls");
		Workbook workbook=Workbook.getWorkbook(Excel);
		Sheet sheet=workbook.getSheet(0);
		int row=sheet.getRows();
		int column=sheet.getColumns();
		
		Object testData[][]=new Object[row-1][column];
		
		for(int i=1;i<row;i++) {
			for(int j=0;j<column;j++) {
				testData[i-1][j]=sheet.getCell(j, i).getContents();
			}
		}
		return testData;
	}
	
	
	@BeforeSuite
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\Selenium\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	
	
	@Test(dataProvider = "LoginData")
	public void LoginTest(String username, String password) {
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement Username=driver.findElement(By.xpath("//input[@id=\"txtUsername\"]"));
		Username.sendKeys(username);
		WebElement Password=driver.findElement(By.xpath("//input[@id=\"txtPassword\"]"));
		Password.sendKeys(password);
		WebElement SubmitBtn=driver.findElement(By.xpath("//input[@id=\"btnLogin\"]"));
		SubmitBtn.click();
	}
	
	@AfterSuite
	public void afterTest() {
		driver.quit();
	}

}
