package Demo;

import org.testng.annotations.Test;

public class readDataFromCmdLine {
	@Test
	public void read()
	{
		String BROWSER=System.getProperty("browser");
		System.out.println(BROWSER);
		
		String URL=System.getProperty("url");
		System.err.println(URL);
		
	}

}
