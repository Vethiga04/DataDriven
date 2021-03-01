package LoginRelatedTCs;


import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class TC01_Login {


	String[][] Data=null;
	
	
	@DataProvider(name="LoginData")
	public String[][] LoginDataProvider() throws BiffException, IOException {
		Data=ExcelRetrieveData();
		return Data;
	}
	
	
	public String[][] ExcelRetrieveData() throws BiffException, IOException {
		FileInputStream ExcelFile=new FileInputStream("C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\DataDriven\\JXL\\Data.xls");
		Workbook workbook=Workbook.getWorkbook(ExcelFile);
		Sheet SheetExcel=workbook.getSheet(0);
		int RowCount= SheetExcel.getRows();
		int ColumnCount=SheetExcel.getColumns();
		
		String TestData[][]=new String[RowCount-1][ColumnCount];
		
		for(int i=1;i<RowCount;i++) {
			for(int j=0;j<ColumnCount;j++) {
				TestData[i-1][j]= SheetExcel.getCell(j,i).getContents();
			}
		}
		return TestData;
		
	}


	
	@Test(dataProvider = "LoginData")
	public void LoginPage(String UserName,String Password) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\Selenium\\chromedriver.exe");
	    WebDriver driver=new ChromeDriver();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		WebElement username=driver.findElement(By.xpath("//input[@id=\"txtUsername\"]"));
		WebElement password=driver.findElement(By.xpath("//input[@id=\"txtPassword\"]"));
		WebElement submitbtn=driver.findElement(By.xpath("//input[@id=\"btnLogin\"]"));
		username.sendKeys(UserName);
		password.sendKeys(Password);
		submitbtn.click();
		driver.quit();
	}
	
	
}
