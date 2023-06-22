package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementation implements IRetryAnalyzer{

	int count=0;
	int reTryCount=4;
	
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		while(count<reTryCount)
		{
			count++;
			return true;
		}
		return false;
	}

}
