package genericUtilities;

import java.util.Random;

/**
 * This class consists of generic methods related to Java
 * @author Veena
 *
 */
public class JavaUtility {
	
	/**
	 * This method will generate random number
	 * @return
	 */
	public int generateRandomNumber()
	{
		Random rand=new Random(10000000);
		int r=rand.nextInt();
		return r;
	}

}
