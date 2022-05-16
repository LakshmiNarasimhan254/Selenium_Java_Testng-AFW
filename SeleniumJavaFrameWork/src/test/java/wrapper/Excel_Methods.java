package wrapper;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Methods {
	XSSFWorkbook wb;
	XSSFSheet ws;

	public Excel_Methods(String strTestName) {
		try {
			wb= new XSSFWorkbook(System.getProperty("user.dir")+ "/src/test/resources/excel/" + strTestName + ".xlsx");
			ws = wb.getSheet("Testdata");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public String getCellValueString(int iRow, int iCol){
		String strCellValue = ws.getRow(iRow).getCell(iCol).getStringCellValue();		
		return strCellValue ;
		
	}
	public double getCellValueNum(int iRow, int iCol){
		double strCellValue = ws.getRow(iRow).getCell(iCol).getNumericCellValue();		
		return strCellValue ;
		
	}

}
