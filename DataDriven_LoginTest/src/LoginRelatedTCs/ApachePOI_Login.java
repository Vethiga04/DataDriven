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

public class ApachePOI_Login {
	int i;
	List<String> UsernameList=new ArrayList<String>();
	List<String> PasswordList=new ArrayList<String>();
	WebDriver driver;
	
	
	public void ReadExcelSheet() throws IOException {
		 FileInputStream ExcelFile=new FileInputStream("C:\\Users\\TheShy\\Documents\\Learning_V\\Automation\\DataDriven\\Apache_POI\\Datas.xlsx");
		 Workbook workbook=new XSSFWorkbook(ExcelFile);
		 Sheet sheet=workbook.getSheetAt(0);
		 Iterator<Row> RowIterator=sheet.iterator();
		 
		 i=1;
		 
		 while(RowIterator.hasNext()) {
			 Row Rowvalue=RowIterator.next();
			 Iterator<Cell> ColumnIterator=Rowvalue.iterator();
			 
			 while(ColumnIterator.hasNext()) {
				 if(i%2==1) {
					 UsernameList.add(ColumnIterator.next().getStringCellValue());
				 }
				 else if(i%2==0) {
					 PasswordList.add(ColumnIterator.next().getStringCellValue());
				 }
				 i++;
			 }
		 }
	}


	public void DataValues() {
		for(int j=0;j<UsernameList.size();j++) {
			LoginTest(UsernameList.get(j), PasswordList.get(j));
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
	
	
	public static void main(String[] args) throws IOException {
		ApachePOI_Login POI_Login=new ApachePOI_Login();
		POI_Login.ReadExcelSheet();
		POI_Login.DataValues();
	}

}