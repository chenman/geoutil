package org.cola.util.geo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class GenKmlUtil {

	public static void main(String[] args) throws IOException {
		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("kml");
		Element document = root.addElement("Document");

		Element name = document.addElement("name");
		name.addText("�����20180720");

		// ��ȡxlsx�ļ�
		XSSFWorkbook xssfWorkbook = null;
		// Ѱ��Ŀ¼��ȡ�ļ�
		File excelFile = new File("doc/cleared.xlsx");
		InputStream is = new FileInputStream(excelFile);
		xssfWorkbook = new XSSFWorkbook(is);

		if (xssfWorkbook == null) {
			System.out.println("δ��ȡ������,����·����");
			return;
		}

		ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		if (xssfSheet == null) {
			return;
		}
		// ����ÿ��sheet����ȡ���е�ÿһ��
		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow == null)
				continue;
			Element placeMark = document.addElement("Placemark");
			Element pName = placeMark.addElement("name");
			Element style = placeMark.addElement("Style");
			/*
			<Style>
				<IconStyle>
					<Icon>
						<href>http://weixin.hui12580.cn:8090/ATPtest/red-pushpin.png</href>
					</Icon>
					<OvIcon>1</OvIcon>
					<OvIconNum>0</OvIconNum>
					<color>ffffffff</color>
					<scale>1.0</scale>
				</IconStyle>
			</Style>
			 */
			Element iconStyle = style.addElement("IconStyle");
			iconStyle.addElement("Icon").addElement("href").addText("http://weixin.hui12580.cn:8090/ATPtest/red-pushpin.png");
			iconStyle.addElement("OvIcon").addText("1");
			iconStyle.addElement("OvIconNum").addText("0");
			iconStyle.addElement("color").addText("ffffffff");
			iconStyle.addElement("scale").addText("1.0");
			Element point = placeMark.addElement("Point").addElement("coordinates");
			pName.addText(getValue(xssfRow.getCell(0)).trim());
			point.addText(String.format("%.6f,%.6f,0", Double.valueOf(getValue(xssfRow.getCell(1)).trim()),
					Double.valueOf(getValue(xssfRow.getCell(2)).trim())));

			XSSFCell cell = xssfRow.getCell(0);
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		format.setIndentSize(4);
		// ��ĳ�ڵ�����Ժ�ֵд��xml�ĵ���
		XMLWriter writer = new XMLWriter(new FileWriter("doc/cleared.xml"), format);
		writer.write(doc);
		writer.close();
	}

	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow == null) {
			return "---";
		}
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			double cur = xssfRow.getNumericCellValue();
			long longVal = Math.round(cur);
			Object inputValue = null;
			if (Double.parseDouble(longVal + ".0") == cur)
				inputValue = longVal;
			else
				inputValue = cur;
			return String.valueOf(inputValue);
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BLANK
				|| xssfRow.getCellType() == xssfRow.CELL_TYPE_ERROR) {
			return "---";
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

}
