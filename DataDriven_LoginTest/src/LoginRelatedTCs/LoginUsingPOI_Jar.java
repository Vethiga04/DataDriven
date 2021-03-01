package LoginRelatedTCs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class LoginUsingPOI_Jar {

	Cell cellvalue;
	Workbook workbook;
	WebDriver driver;
	static List<String> UsernameList=new ArrayList<String>();
	static List<String> PasswordList=new ArrayList<String>();
	
	
	public void ReadExcel() throws IOException {
		FileInputStream Excel=new FileInputStream("C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\DataDriven\\Apache_POI\\Datas.xlsx");
		workbook=new XSSFWorkbook(Excel);
		Sheet sheet=workbook.getSheetAt(0);
		Iterator<Row> RowIterator=sheet.iterator();
		while(RowIterator.hasNext()) 
		{
			Row rowvalue=RowIterator.next();
			Iterator<Cell> ColumnIterator = rowvalue.iterator();

			int i=1;
			while(ColumnIterator.hasNext()) 
			{
				cellvalue= ColumnIterator.next();
				if((i%2==1)&&!(cellvalue.getStringCellValue().contains("UserName"))) {
					UsernameList.add(cellvalue.getStringCellValue());
				}
				else if((i%2==0)&&!(cellvalue.getStringCellValue().contains("Password"))) {
					PasswordList.add(cellvalue.getStringCellValue());
				}
				i++;
			}
			System.out.println(cellvalue);
		}
		
		
	}
	
	
	public void LoginTest(String username,String password) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\Selenium\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement Username=driver.findElement(By.xpath("//input[@id=\"txtUsername\"]"));
		Username.sendKeys(username);
		WebElement Password=driver.findElement(By.xpath("//input[@id=\"txtPassword\"]"));
		Password.sendKeys(password);
		WebElement SubmitBtn=driver.findElement(By.xpath("//input[@id=\"btnLogin\"]"));
		SubmitBtn.click();

		driver.quit();
	}
	
	
	
	public void SizeOfData() {
		for(int j=0;j<UsernameList.size();j++) {
			LoginTest(UsernameList.get(j),PasswordList.get(j));
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LoginUsingPOI_Jar usingPOI=new LoginUsingPOI_Jar();
		usingPOI.ReadExcel();
		usingPOI.SizeOfData();
		
	}

}
