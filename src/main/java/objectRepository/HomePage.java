package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutOption;
	
	@FindBy(linkText="Sign Out")
	private WebElement logoutButton;
	
	public void orgLinkClick()
	{
		orgLink.click();
	}
	
	public void contLinkClick()
	{
		contLink.click();
	}
	
	public void logoutOfApp(WebDriver driver)
	{
		moveToElement(driver, logoutOption);
		explicitWaitUntilElementIsClickable(driver, 200, logoutButton);
		logoutButton.click();
	}

}
