package org.cola.util.geo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Test01 {

	public static void listNodes(Element node) {

		Iterator<Element> iterator = node.elementIterator();
		boolean isPoint;
		while (iterator.hasNext()) {
			Element e = iterator.next();
			isPoint = false;
			Iterator<Element> iterator2 = e.elementIterator();

			while (iterator2.hasNext()) {
				Element e2 = iterator2.next();
				if ("MultiGeometry".equals(e2.getName())) {
					isPoint =true;
					break;
				}
			}
			if(isPoint) {
				node.remove(e);
			}
			listNodes(e);
		}
	}

	public static void main(String args[]) throws DocumentException, IOException {
		// 1
		SAXReader reader = new SAXReader();

		// 2
		Document doc = reader.read(new File("doc/403.kml"));

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

		// ��ĳ�ڵ�����Ժ�ֵд��xml�ĵ���
		XMLWriter writer = new XMLWriter(new FileWriter("doc/403p.xml"));
		writer.write(doc);
		writer.close();

	}

}
