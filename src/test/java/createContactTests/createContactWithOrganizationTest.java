package createContactTests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoVerifyPage;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.NewContactPage;
import objectRepository.NewOrgPage;
import objectRepository.createNewContactPage;

public class createContactWithOrganizationTest {
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
		
		CreateNewOrgPage cnop=new CreateNewOrgPage(driver);
		cnop.clickOncreateNeworgImg();
				
		//Create new Organization with mandatory details
		NewOrgPage nop=new NewOrgPage(driver);
		nop.createnewOrgAndSave(ORGNAME);
		
		//Navigate to Contacts
		
		//WebElement contactsLink=driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']"));
		//wait.until(ExpectedConditions.elementToBeClickable(contactsLink));
		//contactsLink.click();
	    hp.contLinkClick();
	    createNewContactPage cncp=new createNewContactPage(driver);
		cncp.clickOnCreatenewContact();
		
		//Create new contact with mandatory details
	     NewContactPage ncp=new NewContactPage(driver);
	     ncp.enterLastNameWithOrgAndSave(driver, CONTACTNAME, ORGNAME);
		//validate
	     ContactInfoVerifyPage cnip=new ContactInfoVerifyPage(driver);
		String contactName=cnip.textVerify();
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
