package createContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;


public class createContact {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		
		//creating object for all utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		WebDriver driver=null;
		
		//read data from property file
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		
		int r=jUtil.generateRandomNumber();
		
		//fetching data from excel
		String LASTNAME=eUtil.readDataFromExcel("Contact", 1, 2)+r;
		
		
		//launch browser
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
				WebDriverManager.chromedriver().setup();
				 driver=new ChromeDriver();
				 System.out.println(BROWSER+" browser launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println(BROWSER+ " browser laucnhed");
		}
		
		else
		{
			System.out.println("browser not launched");
		}
				wUtil.implicitelyWait(driver, 2000);
				wUtil.maximizeBrowser(driver);
				//Login to App
				wUtil.accessURL(driver, URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Navigate to Contacts
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				
				//Create new contact with mandatory details
				
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				
				//Save 
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//validate
				String contactName=driver.findElement(By.className("dvHeaderText")).getText();
				if(contactName.contains(LASTNAME))
				{
					System.out.println("contact updated successfully");
				}
				else
				{
					System.out.println("contact not updated");
				}
				
				//and logout
				WebElement logoutButton=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
                wUtil.moveToElement(driver, logoutButton);
				
               WebElement logout= driver.findElement(By.linkText("Sign Out"));
                wUtil.explicitelyWaitUntilVisibilityOfElement(driver, 100, logout);
                logout.click();
                
	}

}
