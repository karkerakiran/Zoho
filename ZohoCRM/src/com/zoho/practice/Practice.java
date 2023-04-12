package com.zoho.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practice {

	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	
	public static void main(String[] args) throws IOException {
		WebDriver driver=new ChromeDriver();
		FileInputStream fis= new FileInputStream("./data/zohocommondata.property");
		Properties p=new Properties();
		p.load(fis);
		String url = p.getProperty("url");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.id("userName")).sendKeys(p.getProperty("username"));
		driver.findElement(By.id("passWord")).sendKeys(p.getProperty("password"));
		driver.findElement(By.xpath("//input[@title='Sign In']")).click();
		
		
	}
	
	
}
