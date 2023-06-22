package createContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class createContactWithOrganization {

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
		
		//read data from excel file
		String ORGNAME=eUtil.readDataFromExcel("Organization", 0, 2)+r;
		String CONTACTNAME=eUtil.readDataFromExcel("Contact", 1, 2)+r;
		
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
			System.out.println("browser not launched");
		}
		
		wUtil.maximizeBrowser(driver);
		wUtil.implicitelyWait(driver, 200);
		 
		 
		//Login to App
		wUtil.accessURL(driver, URL);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Navigate to Organizations
		HomePage hp=new HomePage(driver);
		hp.orgLinkClick();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
		//Create new Organization with mandatory details
		
		
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
				
		//Save 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Navigate to Contacts
		
		//WebElement contactsLink=driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']"));
		//wait.until(ExpectedConditions.elementToBeClickable(contactsLink));
		//contactsLink.click();
		hp.contLinkClick();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Create new contact with mandatory details
		driver.findElement(By.name("lastname")).sendKeys(CONTACTNAME);
		
		//Choose any existing organization
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		Set<String> windows=driver.getWindowHandles();
		
		//switching to child window
		for(String window:windows)
		{
			driver.switchTo().window(window);
			if(driver.getTitle().contains("Accounts"))
			{
				break;
			}
		}
		
		//choosing organization
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
		
		//switching control back to parent window
		
		for(String window:windows) {
			driver.switchTo().window(window);
			if(driver.getTitle().contains("Contacts"))
			{
				break;
			}
			
		}
		
		//Save 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//validate
		String contactName=driver.findElement(By.className("dvHeaderText")).getText();
		if(contactName.contains(CONTACTNAME))
		{
			System.out.println("contact updated successfully");
		}
		else
		{
			System.out.println("contact not updated");
		}
		
		//and logout
		hp.logoutOfApp(driver);
		
		

	}

}
