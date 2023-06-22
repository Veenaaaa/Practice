package Demo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	
	@Test(dataProvider="phones")
	public void readData(String comp,String model,int price)
	{
		System.out.println(comp+ "  "+model+"  "+price);
	}
	
	@DataProvider(name="phones")
	public Object[][] getData()
	{
		Object[][] data=new Object[4][3];
		data[0][0]="Samsung";
		data[0][1]="A80";
		data[0][2]=100;
		
		data[1][0]="Apple";
		data[1][1]="13pro";
		data[1][2]=200;
		
		data[2][0]="vivo";
		data[2][1]="v20";
		data[2][2]=400;
		
		data[3][0]="nokia";
		data[3][1]="m3";
		data[3][2]=900;
		
		return data;
	}

}
