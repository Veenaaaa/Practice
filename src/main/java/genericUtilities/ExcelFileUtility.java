package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of generic methods related to excel file
 * @author Veena
 *
 */
public class ExcelFileUtility {
	/**
	 * This method reads data from excel and returns to caller
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	
	public String readDataFromExcel(String sheetName, int rowNo, int cellNo) throws Throwable
	{
		FileInputStream fis=new FileInputStream(iConstantUtility.excelFilePath);
		Workbook book=WorkbookFactory.create(fis);
		String value=book.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
		return value;
	}
	/**
	 * This method will write data into excel file
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeDataIntoExcel(String sheetName, int rowNo, int cellNo,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(iConstantUtility.excelFilePath);
		Workbook book=WorkbookFactory.create(fis);
		book.createSheet(sheetName).createRow(rowNo).createCell(cellNo).setCellValue(value);
		FileOutputStream fos=new FileOutputStream(iConstantUtility.excelFilePath);
		book.write(fos);
		
	}
	
	public Object[][] readDataForDataProviders(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream(iConstantUtility.excelFilePath);
		Workbook book=WorkbookFactory.create(fis);
		Sheet sh=book.getSheet(sheetName);
		int lastRow=sh.getLastRowNum();
		int lastCell=sh.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[lastRow][lastCell];
		
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
				
			}
		}
		return data;
	}

}
