package org.cola.util.geo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeoUtilTest04 {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException {
		File excelFile = new File("doc/tmp001.xlsx");
		InputStream is = new FileInputStream(excelFile);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		

        File outFile = new File("doc/out001.xlsx");
		XSSFWorkbook outWorkbook = new XSSFWorkbook();
		XSSFSheet outSheet = outWorkbook.createSheet("0");

		if (xssfWorkbook == null) {
			System.out.println("读取excel失败！");
			return;
		}

		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		if (xssfSheet == null) {
			return;
		}
		for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow == null)
				continue;
			Double lon =  Double.valueOf(getValue(xssfRow.getCell(0)));
			Double lat =  Double.valueOf(getValue(xssfRow.getCell(1)));
			
			Point dst = GeoUtil.convertEarth2Mars(new Point(lon,lat));
			
			XSSFRow row = outSheet.createRow(rowNum);
			row.createCell(0).setCellValue(lon);
			row.createCell(1).setCellValue(lat);
			row.createCell(2).setCellValue(dst.getLon());
			row.createCell(3).setCellValue(dst.getLat());
			
		}
		FileOutputStream fileoutputStream = new FileOutputStream(outFile);
		outWorkbook.write(fileoutputStream);
        fileoutputStream.close();
		xssfWorkbook.close();
	}
	

	@SuppressWarnings("deprecation")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow == null) {
			return "---";
		}
		if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double cur = xssfRow.getNumericCellValue();
			long longVal = Math.round(cur);
			Object inputValue = null;
			if (Double.parseDouble(longVal + ".0") == cur)
				inputValue = longVal;
			else
				inputValue = cur;
			return String.valueOf(inputValue);
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_BLANK
				|| xssfRow.getCellType() == Cell.CELL_TYPE_ERROR) {
			return "---";
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

}
