package org.cola.util.geo;

import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;

public class GeoUtilTest01 {
	
	public static void listNodes(Element node){  
        if(!(node.getTextTrim().equals("")) && "coordinates".equals(node.getName())){  
             //Log.d("��ǰ������ݣ�", node.getText());
             String[] coo = node.getTextTrim().split(",");
             Point src = new Point(Double.valueOf(coo[0]), Double.valueOf(coo[1]));
             
             Point dst;
             String dstStr;
             //119.040339,25.446802
             //119.040266,25.447595
             System.out.println(String.format("%.6f,%.6f,%s", src.getLon(),src.getLat(),coo[2]));
             
             
             dst = GeoUtil.convertBaidu2Earth(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertBaidu2Mars(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertEarth2Baidu(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertEarth2Mars(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertMapbar2Earth(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertMapbar2Mars(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertMars2Baidu(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);

             dst = GeoUtil.convertMars2Earth(src);
             dstStr = String.format("%.6f,%.6f,%s", dst.getLon(),dst.getLat(),coo[2]);
             System.out.println(dstStr);
        }  
        
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            listNodes(e);  
        }
    }

	public static void main(String[] args) {
		try {
			// 1
			SAXReader reader = new SAXReader();

			// 2
			Document doc = reader.read(new File("doc/test01.kml"));

			/*
			 * 3 Document�ṩ�˷���: Element getRootElement() �÷�����������ȡXML�ĵ��еĸ�Ԫ�أ�
			 * ����emplist.xml�ĵ����ԣ���Ԫ�ؾ��� <list>��ǩ��
			 *
			 * Element�� ÿһ��Elementʵ�������Ա�ʾXML�ĵ��е� һ��Ԫ�أ���:һ�Ա�ǩ��
			 */
			Element root = doc.getRootElement();
			/*
			 * Element�ṩ�˷���: String getName() �÷������Ի�ȡ��ǰԪ�ص�����(��ǩ��)
			 */
			System.out.println("��ȡ�˸�Ԫ��:" + root.getName());
			
			listNodes(root);

	        //��ĳ�ڵ�����Ժ�ֵд��xml�ĵ���
	        XMLWriter writer = new XMLWriter(new FileWriter("doc/out01.xml"));
	        writer.write(doc);
	        writer.close();
	        
//			// 4
//			/*
//			 * ��ȡһ��Ԫ���е���Ԫ�� Element�ṩ����ط���:
//			 *
//			 * 1 Element element(String name) ��ȡ��ǰԪ����ָ�����ֵ���Ԫ�ء�
//			 *
//			 * 2: List elements() ��ȡ��ǰԪ����������Ԫ��
//			 *
//			 * 3: List elements(String name) ��ȡ��ǰԪ��������ͬ����Ԫ��
//			 *
//			 * 2,3���صļ����е�ÿһ��Ԫ�ض���Element ��ʵ����ÿ��ʵ����ʾ���е�һ����Ԫ�ء�
//			 *
//			 */
//			// ��ȡ����emp��ǩ
//			List<Element> list = root.elements();
//
//			for (Element empEle : list) {
//				// ��ȡԱ������
//				Element nameEle = empEle.element("name");
//				/*
//				 * Element���ṩ�˻�ȡ��ǰԪ�����ı��ķ���: String getText(),String getTextTrim()
//				 */
//				String name = nameEle.getText();
//				System.out.println("name:" + name);
//
//				// ��ȡԱ������
//				int age = Integer.parseInt(empEle.elementText("age"));
//
//				// ��ȡ�Ա�
//				String gender = empEle.elementText("gender");
//
//				// ��ȡ����
//				int salary = Integer.parseInt(empEle.elementText("salary"));
//				/*
//				 * Attribute attribute(String name) ��ȡ��ǰԪ��(��ǩ)��ָ�����ֵ�����
//				 *
//				 * Attribute��ÿһ��ʵ�����ڱ�ʾһ�� ���ԡ����г��÷���: String getName():��ȡ������ String
//				 * getValue():��ȡ����ֵ
//				 */
//				Attribute attr = empEle.attribute("id");
//				int id = Integer.parseInt(attr.getValue());
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
