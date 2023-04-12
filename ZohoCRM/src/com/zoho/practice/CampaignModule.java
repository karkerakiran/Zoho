package com.zoho.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CampaignModule {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}

	public static void main(String[] args) throws IOException, InterruptedException {
	
		FileInputStream fis=new FileInputStream("./data/zohocommondata.property");
		Properties p=new Properties();
		p.load(fis);
		FileInputStream fis1=new FileInputStream("./data/zohotestdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(p.getProperty("url"));
		driver.findElement(By.id("userName")).sendKeys(p.getProperty("username"));
		driver.findElement(By.id("passWord")).sendKeys(p.getProperty("password"));
		driver.findElement(By.xpath("//input[@title='Sign In']")).click();
		
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//input[@value='New Campaign']")).click();
		driver.findElement(By.name("property(Campaign Name)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(0).getStringCellValue());
		driver.findElement(By.name("property(Start Date)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(1).getStringCellValue());
		driver.findElement(By.name("property(Expected Revenue)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(2).getStringCellValue());
		driver.findElement(By.name("property(Actual Cost)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(3).getStringCellValue());
		driver.findElement(By.name("property(Num sent)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(4).getStringCellValue());
		
		WebElement type = driver.findElement(By.name("property(Type)"));
		Select s=new Select(type);
		s.selectByIndex(7);
		
		Thread.sleep(3000);
		WebElement status= driver.findElement(By.name("property(Status)"));
		Select s1=new Select(status);
		s1.selectByIndex(2);
		
		driver.findElement(By.name("property(End Date)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(5).getStringCellValue());
		driver.findElement(By.name("property(Budgeted Cost)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(6).getStringCellValue());
		driver.findElement(By.name("property(Expected Response)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(7).getStringCellValue());
		driver.findElement(By.name("property(Description)")).sendKeys(wb.getSheet("zohocampaign").getRow(1).getCell(8).getStringCellValue());
		driver.findElement(By.xpath("(//input[@value='Save'])[2]")).click();
		


		
	}

}
