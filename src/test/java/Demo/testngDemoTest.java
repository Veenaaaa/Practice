package Demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testngDemoTest {
	
	@Test
	public void sampleTest()
	{
		System.out.println("hello");
		
	}
	@Test
	public void abcTest()
	{
		System.out.println("abc");
	}
	@Test(enabled=false)
	public void defTest()
	{
		System.out.println("def");
	}

}
