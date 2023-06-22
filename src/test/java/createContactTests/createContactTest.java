package createContactTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoVerifyPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.NewContactPage;
import objectRepository.createNewContactPage;

@Listeners(genericUtilities.ListenersImplementation.class)
public class createContactTest extends BaseClass{
	//@Test(groups="smokeSuite")
	@Test/*(retryAnalyzer=genericUtilities.RetryAnalyzerImplementation.class)*/
	public void createContTest() throws Throwable
	{
		//fetching data from excel
		String LASTNAME=eUtil.readDataFromExcel("Contact", 1, 2)+jUtil.generateRandomNumber();
		
				//Navigate to Contacts
				HomePage hp=new HomePage(driver);
				hp.contLinkClick();
				createNewContactPage cnp=new createNewContactPage(driver);
				cnp.clickOnCreatenewContact();
				Reporter.log("navigated to contacts",true);
				
				//Create new contact with mandatory details
				NewContactPage ncp=new NewContactPage(driver);
				ncp.enterLastNameAndSave(LASTNAME);
			
				Reporter.log("created contact with mandatory details",true);
				
				//validate
				ContactInfoVerifyPage cnvp=new ContactInfoVerifyPage(driver);
				String contactName=cnvp.textVerify();
				Assert.assertTrue(contactName.contains(LASTNAME));
				Reporter.log("validated successfully",true);
				
	}
	
	@Test
	public void demoTest()
	{
		System.out.println("this is demo");
	}


}
