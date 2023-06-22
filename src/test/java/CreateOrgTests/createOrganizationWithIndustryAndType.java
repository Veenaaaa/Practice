package CreateOrgTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class createOrganizationWithIndustryAndType {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		WebDriver driver=null;
		
		//creating object for all utilities
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//read data from property file
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		
		int r=jUtil.generateRandomNumber();
		
		//read data from excel
		String ORGNAME=eUtil.readDataFromExcel("Organization", 7, 2)+r;
		String INDUSTRY=eUtil.readDataFromExcel("Organization", 7, 3);
		String TYPE=eUtil.readDataFromExcel("Organization", 7, 4);
		
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
			System.out.println(BROWSER+" browser launched");
		}
		else
		{
			System.out.println("Browser not launched");
		}
		wUtil.maximizeBrowser(driver);
		wUtil.implicitelyWait(driver, 1000);
		
		//Login to App
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to Organizations
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Create new Organization with mandatory details
		
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Choose Electronics in the Industry dropdown
		WebElement dropdown=driver.findElement(By.name("industry"));
		wUtil.SelectFromDropdown(INDUSTRY, dropdown);
		
		//Choose Investor in Type drop down
		WebElement TypeDropDown=driver.findElement(By.name("accounttype"));
		wUtil.SelectFromDropdown(TYPE, TypeDropDown);
		
		//Save 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//validate
		String text=driver.findElement(By.className("dvHeaderText")).getText();
		if(text.contains(ORGNAME))
		{
			System.out.println("organization updated successfully");
		}
		
		else
		{
			System.out.println("organization not updated");
		}
		//logout
		WebElement logoutButton=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.moveToElement(driver, logoutButton);
		
		WebElement logOut=driver.findElement(By.linkText("Sign Out"));
		wUtil.explicitWaitUntilElementIsClickable(driver, 1000, logOut);
		logOut.click();

	}

}
