package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class NewOrgPage extends WebDriverUtility{
	
	public NewOrgPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public void createnewOrgAndSave(String orgName)
	{
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createnewOrgWithIndustryAndSave(String orgName, String industry)
	{
		orgNameEdt.sendKeys(orgName);	
		SelectFromDropdown(industry, industryDropDown);
		saveBtn.click();
	}
	
	public void createnewOrgWithIndustryTypeAndSave(String orgName, String industry,String type)
	{
		orgNameEdt.sendKeys(orgName);	
		SelectFromDropdown(industry, industryDropDown);
		SelectFromDropdown(type, typeDropDown);
		saveBtn.click();
	}

}
