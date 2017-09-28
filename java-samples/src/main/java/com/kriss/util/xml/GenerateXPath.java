package com.kriss.util.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl;

public class GenerateXPath {
	
	private static int depth = 0;

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("C:/Work/Projects_Rave/Rave_XML/11764A(DEV)/sample.xml"));
			//doc.getDocumentElement().normalize();
			
			//ThreadUtil.printAllStackTrace();
			
			Node rootNode = doc.getDocumentElement();
			printNodeValues(rootNode);
			
			/*XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("");*/
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printArrtibuteValues(Node node) {
		NamedNodeMapImpl impl = (NamedNodeMapImpl) node.getAttributes();
		int len = impl.getLength();
		for(int i=0; i<len; i++){
			Node att = impl.item(i);
			//System.out.print(" " + att.getNodeName() + "\\" + att.getNodeValue());
		}
	}
	
	public static void printNodeValues(Node parent) {
		depth++;
		System.out.print("<"+parent.getNodeName()+">");
		printArrtibuteValues(parent);
		
		NodeList childs = parent.getChildNodes();
		int len = childs.getLength();
		
		// Learning
		/*System.out.print(" Length : " + len);
		for(int i=0; i<len; i++) {
			Node child = childs.item(i);
			System.out.print(" Type- " + child.getNodeType());
		}*/
		
		for(int i=0; i<len; i++) {
			Node child = childs.item(i);
			System.out.println();
			for(int j=0; j<depth; j++) System.out.print("\t");

			if(child.getNodeType() == Node.ELEMENT_NODE) printNodeValues(child);
			else if(child.getNodeType() == Node.TEXT_NODE) System.out.print(" " + child.getTextContent());
		}
		depth--;
	}
}
