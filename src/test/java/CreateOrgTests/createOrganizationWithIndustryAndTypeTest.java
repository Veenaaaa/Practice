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

public class createOrganizationWithIndustryAndTypeTest extends BaseClass {
	@Test
	public void createOrgWithIndType() throws Throwable
	{
		//read data from excel
		String ORGNAME=eUtil.readDataFromExcel("Organization", 7, 2)+jUtil.generateRandomNumber();
		String INDUSTRY=eUtil.readDataFromExcel("Organization", 7, 3);
		String TYPE=eUtil.readDataFromExcel("Organization", 7, 4);
		
		//Navigate to Organizations
		HomePage hp=new HomePage(driver);
		hp.orgLinkClick();
		
		CreateNewOrgPage cnop=new CreateNewOrgPage(driver);
		cnop.clickOncreateNeworgImg();
		
		//Create new Organization with mandatory details
		NewOrgPage nop=new NewOrgPage(driver);
		nop.createnewOrgWithIndustryTypeAndSave(ORGNAME, INDUSTRY, TYPE);
		
		//validate
		OrgInfoVerifyPage oivp=new OrgInfoVerifyPage(driver);
		String text=oivp.textVerify();
		Assert.assertTrue(text.contains(ORGNAME));
		
		
	}


}
