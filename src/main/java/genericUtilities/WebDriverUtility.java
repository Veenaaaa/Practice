package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists all methods related to web driver
 * @author Veena
 *
 */
public class WebDriverUtility {
	
	public void accessURL(WebDriver driver,String URL)
	{
		driver.get(URL);
	}
	
	/**
	 * This method helps to maximize browser
	 * @param driver
	 */
	public void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method helps to minimize browser
	 * @param driver
	 */
	public void minimizeBrowser(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method applies logic of implicit wait
	 * @param driver
	 * @param seconds
	 */
	public void implicitelyWait(WebDriver driver, int seconds)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	/**
	 * This method will wait till visibility of element
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void explicitelyWaitUntilVisibilityOfElement(WebDriver driver, int seconds,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method waits till element is clickeable
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void explicitWaitUntilElementIsClickable(WebDriver driver, int seconds, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * this method chooses from dropdown as per the index given
	 * @param element
	 * @param index
	 */
	
	public void SelectFromDropdown(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
		
	}
	
	/**
	 * This method chooses from dropdown as per visible text
	 * @param element
	 * @param visibleText
	 */
	public void SelectFromDropdown(WebElement element,String visibleText)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(visibleText);
		
	}
    
	/**
	 * this method chooses from drop down as per value
	 * @param value
	 * @param element
	 */
	public void SelectFromDropdown(String value,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
		
	}
	
	/**
	 * This method will move the mouse to element specified
	 * @param driver
	 * @param element
	 */
	public void moveToElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void windowHandling(WebDriver driver,String title)
	{
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows)
		{
			driver.switchTo().window(window);
			if(driver.getTitle().contains(title))
			{
				break;
			}
		}
	}
	
	public String takeScreenshot(WebDriver driver,String screenshotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(".\\screenshots\\"+screenshotName+".png");
		Files.copy(src, dst);
		return dst.getAbsolutePath();
	}
	
	
}
