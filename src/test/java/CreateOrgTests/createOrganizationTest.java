package CreateOrgTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.NewOrgPage;
import objectRepository.OrgInfoVerifyPage;

public class createOrganizationTest extends BaseClass{
	//@Test(groups="smokeSuite")
	@Test
	public void createNewOrgTest() throws Throwable
	{
		
		int r=jUtil.generateRandomNumber();
		
		//read data from excel file
		String ORGNAME=eUtil.readDataFromExcel("Organization", 1, 2)+jUtil.generateRandomNumber();
		
		//Navigate to Organizations
		HomePage hp=new HomePage(driver);
		hp.orgLinkClick();
		
		CreateNewOrgPage cnop=new CreateNewOrgPage(driver);
		cnop.clickOncreateNeworgImg();
		
		//Create new Organization with mandatory details
		NewOrgPage nop=new NewOrgPage(driver);
		nop.createnewOrgAndSave(ORGNAME);
		
		//validate
		OrgInfoVerifyPage oivp=new OrgInfoVerifyPage(driver);
		String text=oivp.textVerify();
		Assert.assertTrue(text.contains(ORGNAME));
		
		
	}

}
