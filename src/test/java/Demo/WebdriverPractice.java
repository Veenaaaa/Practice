package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverPractice {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		//System.out.println(driver.getTitle());
		//System.out.println(driver.getCurrentUrl());
		//System.out.println(driver.getPageSource());
		
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("mobile");
		search.submit();
		//driver.findElement(By.xpath("//span[text()='Become a Seller']")).click();
		//WebElement number=driver.findElement(By.xpath("//input[@name='registrationNumber']"));
		//number.sendKeys("1234567");
		//Thread.sleep(1000);
		//number.submit();
		//number.clear();
		//Dimension num=number.getSize();
		//System.out.println(num);
		
		
	}

}
