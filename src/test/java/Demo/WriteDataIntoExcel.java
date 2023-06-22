package Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcel {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\ExcelDataNew.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		
		Sheet sh=book.createSheet("Demo");
		sh.createRow(0).createCell(3).setCellValue("Sudheer");
		
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\ExcelDataNew.xlsx");
		book.write(fos);

	}

}
