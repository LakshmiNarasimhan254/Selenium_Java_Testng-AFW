package reports;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reports{
	
public static XSSFWorkbook wb = null;
public static XSSFSheet sheet = null;
public static XSSFRow row=null;
String tcSheetName;
int iCount;
DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
Date sDate = new Date();
String dDate = dfDate.format(sDate);
String sTestRptPath;

//Defining the constructor- This take 
	public Excel_Reports(String testCaseSheetName) throws InvalidFormatException, IOException{

		tcSheetName=testCaseSheetName;
		sTestRptPath = System.getProperty("user.dir")+"/Report/"+ tcSheetName + "/" + dDate + "/";
		//System.out.println(sTestRptPath);
		File file = new File(sTestRptPath);
		if(!file.exists()){
			file.mkdirs();
		}
		wb = new XSSFWorkbook();
		sheet = wb.createSheet(testCaseSheetName);
	    reportHeader(); 
		}
//Method to create Report headers	
	public void reportHeader(){
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("S.No");
		row.createCell(1).setCellValue("Description");
		row.createCell(2).setCellValue("Status");
		row.createCell(3).setCellValue("Screenshot");
		}
//Method to close the workbook				
	public void closeWorkbook(){
		File file = new File(sTestRptPath);
		File[] files = file.listFiles();
		System.out.println(files.length);
		if (files != null) {
			iCount = files.length;
			iCount = iCount + 1;
			//System.out.println("Hi");
		}
		else {
			iCount = 1;
		}
	    FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(sTestRptPath+ "Run"+iCount+".xlsx"));
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		} 
//Method to report test case status 		
	public  void reportEvent(String desc,String status,String sPath){
		int newRow= sheet.getLastRowNum()+1;
		row = sheet.createRow(newRow);			
		row.createCell(0).setCellValue("Step " + newRow);
		row.createCell(1).setCellValue(desc);
		row.createCell(2).setCellValue(status);
		closeWorkbook();
		}
	
//Method for Cell Formating	
	public void formatCell(XSSFRow row,int cellVal){
		row.getCell(cellVal);
	}
	
	public  void reportSnapshot(String sPath){
		String sPath1;
		int newRow= sheet.getLastRowNum();
		row = sheet.createRow(newRow);			
		CreationHelper createHelper = wb.getCreationHelper();
	    CellStyle hlink_style = wb.createCellStyle();
	    Font hlink_font = wb.createFont();
	    hlink_font.setUnderline(Font.U_SINGLE);
	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
	    hlink_style.setFont(hlink_font);
	    //Hyperlink hp = createHelper.createHyperlink(Hyperlink.LINK_FILE)
		//Hyperlink hp = createHelper.createHyperlink(Hyperlink.LINK_FILE);
		sPath1 =sPath +newRow +".png";
		sPath1=sPath1.replace("\\", "/");
		System.out.println(sPath1);
	   // hp.setAddress(sPath1);
	    row.createCell(3).setCellValue(sPath);
	   // row.getCell(3).setHyperlink(hp);
	    row.getCell(3).setCellStyle(hlink_style);
		}
		
}
	

	
	

