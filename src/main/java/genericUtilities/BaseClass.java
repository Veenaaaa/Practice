package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.HomePage;
import objectRepository.LoginPage;


public class BaseClass {
	public PropertyFileUtility pUtil=new PropertyFileUtility();
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public JavaUtility jUtil=new JavaUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sDriver;
	
	
	//@BeforeSuite(groups="smokeSuite")
	@BeforeSuite
	public void bsConfig()
	{
		System.out.println("==database connection successful==");
	}
	//@BeforeClass(groups="smokeSuite")
	//@Parameters("browser")
	@BeforeClass
	public void bcConfig(/*String BROWSER*/) throws Throwable
	{
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		
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
					System.out.println("Browser not launched");
				}
				sDriver=driver;
				wUtil.maximizeBrowser(driver);
				wUtil.implicitelyWait(driver, 300);
				driver.get(URL);
				System.out.println("==launched browser==");
	}
	
	//@BeforeMethod(groups="smokeSuite")
	@BeforeMethod
	public void bmConfig() throws Throwable
	{
		String USERNAME=pUtil.readDataFromPropertyFile("username");
        String PASSWORD=pUtil.readDataFromPropertyFile("password");
    	LoginPage lp=new LoginPage(driver);
    	lp.loginToApp(USERNAME, PASSWORD);
    	System.out.println("==logged into application==");
	}
	//@AfterMethod(groups="smokeSuite")
	@AfterMethod
	public void amConfig()
	{
		HomePage hp=new HomePage(driver);
		hp.logoutOfApp(driver);
		System.out.println("==logged out of app==");
	}
	
	//@AfterClass(groups="smokeSuite")
	@AfterClass
	public void acConfig()
	{
		driver.close();
		System.out.println("==browser closed==");
	}
	
	//@AfterSuite(groups="smokeSuite")
	@AfterSuite
	public void asConfig()
	{
		System.out.println("==database connection closed==");
	}

}
