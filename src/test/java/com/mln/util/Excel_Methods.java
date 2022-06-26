package com.mln.util;

import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
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

	public Object[][] getexceldata(){
		int irow = ws.getPhysicalNumberOfRows();
		int icol = ws.getRow(0).getPhysicalNumberOfCells();
		Object [][] celldata =new Object[irow][icol];
		for (int i=0; i<irow;i++){
			for(int j =0;j<icol;j++){
				celldata[i][j]=ws.getRow(i).getCell(j).getStringCellValue();							
			}		
		}		
		return celldata;
	}

}



