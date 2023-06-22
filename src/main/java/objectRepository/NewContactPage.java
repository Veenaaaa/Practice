package objectRepository;

import java.awt.Window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class NewContactPage extends WebDriverUtility{
	public NewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//input[@name='account_id']/following-sibling::img")
    private WebElement selectOrgLookupImg;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	
	public void enterLastNameAndSave(String lastName)
	{
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void enterLastNameWithOrgAndSave(WebDriver driver,String lastName,String OrgName)
	{
		lastNameEdt.sendKeys(lastName);
		selectOrgLookupImg.click();
		windowHandling(driver, "Accounts");
		searchEdt.sendKeys(OrgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[@href='"+OrgName+"']"));
		windowHandling(driver, "Contacts");
		saveBtn.click();
		
	}
}
