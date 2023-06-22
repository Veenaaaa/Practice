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

public class createOrganizationWithIndustryTest extends BaseClass{
@Test
public void createOrgWithIndTest() throws Throwable
{
		//read data from excel file
		String ORGNAME=eUtil.readDataFromExcel("Organization", 4, 2)+jUtil.generateRandomNumber();
		String INDUSTRY=eUtil.readDataFromExcel("Organization", 4, 3);
		
				//Navigate to Organizations
				HomePage hp=new HomePage(driver);
				hp.orgLinkClick();
				CreateNewOrgPage cnop=new CreateNewOrgPage(driver);
				cnop.clickOncreateNeworgImg();
				
				//Create new Organization with mandatory details
				NewOrgPage nop=new NewOrgPage(driver);
				nop.createnewOrgWithIndustryAndSave(ORGNAME, INDUSTRY);
				
				
				//validate
				OrgInfoVerifyPage oivp=new OrgInfoVerifyPage(driver);
				String text=oivp.textVerify();
				Assert.assertTrue(text.contains(ORGNAME));
				
	}

}
