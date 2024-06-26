package com.pinde.core.util;

import com.pinde.core.exception.GeneralException;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.NodeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XmlParse {

	private static Logger logger = LoggerFactory.getLogger(XmlParse.class);
	
	//xml对象
	private Document document;
	
	private Element rootElement;
		
	public XmlParse(String xml) throws GeneralException{
        try {
			this.document = DocumentHelper.parseText(xml);
			this.rootElement = this.document.getRootElement();
		} catch (DocumentException e) {
			logger.error( e.getMessage(),e);
			throw new GeneralException( e);
		}
	}

	public XmlParse(File xml) throws GeneralException{
        try {
        	SAXReader reader = new SAXReader();
        	this.document = reader.read(xml);
			this.rootElement = this.document.getRootElement();
		} catch (DocumentException e) {
			logger.error( e.getMessage(),e);
			throw new GeneralException( e);
		}
	}

	/**
	 * 判断两个元素是否有相同的名字和值（值可以比较）
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compare(Element a, Element b) {
		return a.getName().equals(b.getName()) && (new NodeComparator().compare(a, b) == 0 ? true : false);
	}
	
//	public XmlParse() throws GeneralException{
//		this.document = DocumentHelper.createDocument();
//	}

	public Element getRootElement() {
		return rootElement;
	}

//	protected Node getNode(Node note, String xpath) {
//		Node ret = null;
//		if (note != null && xpath != null) {
//				ret = (Node) note.selectSingleNode(xpath);
//		}
//		return ret;
//	}
	
	/**
	 * 获得单个节点
	 * @param xpath 节点路径
	 * @return
	 */
	protected Node getNode(String xpath) {
		Node ret = null;
		if (StringUtil.isNotBlank(xpath)) {
			ret = (Node) document.selectSingleNode(xpath);
		}
		return ret;
	}
	
	/**
	 * 获得多个节点
	 * @param xpath 节点路径
	 * @return
	 */
	protected List<Node> getNodes(String xpath) {
		List<Node> ret = null;
		if (StringUtil.isNotBlank(xpath)) {
				ret = (List<Node>) document.selectNodes(xpath);
		}
		return ret;
	}
	
//	protected Element createElement(String name, String value) {		
//		Element element = DocumentHelper.createElement(name);
//		if (value != null) {
//			element.setText(value);
//		}
//		return element;
//	}

//	protected void makeElement(String xpath){
//		DocumentHelper.makeElement(document, xpath);
//	}
	
//	protected String getText(String xpath) {
//		String ret = null;
//		Node node = getNode(xpath);
//		ret = getNodeText(node);
//		return ret;
//	}

	/**
	 * 获得节点的文本
	 * @param node 节点对象
	 * @return
	 */
	protected String getNodeText(Node node) {
		String ret = null;
		if (node != null) {
			ret = node.getText();
		}
		ret = StringUtil.defaultString(ret).trim();
		return ret;
	}
	
//	protected String removeSigle(String xpath){
//		List<Node> nodeList = getNodes(xpath);
//		if(nodeList.size()==1){
//			Node node = nodeList.get(0);
//			node.getParent().remove(node);
//			return node.asXML();
//		}
//		return "";
//	}

	/**
	 * 获得多个节点的文本
	 *
	 * @param xpath 节点路径
	 * @return
	 */
	protected List<String> getTexts(String xpath) {
		List<Node> nodes = getNodes(xpath);
		List<String> texts = new ArrayList<String>();
		for (Node node : nodes) {
			texts.add(getNodeText(node));
		}
		return texts;
	}
	
	/**
	 * 为指定路径的单个节点添加属性
	 * @param xpath 路径
	 * @param attributeName 属性名
	 * @param attributeValue 属性值
	 * @throws GeneralException
	 */
	protected void addSingeAttribute(String xpath,String attributeName,String attributeValue) throws GeneralException{
		List<Node> elmentList = getNodes(xpath);
		if (elmentList.size() > 1) {
			throw new GeneralException("xml解析错误!");
		}else if(elmentList.size()==1){
			Node node = elmentList.get(0);
			if (!(node instanceof Element)) {
				throw new GeneralException("xml解析错误!");
			}
			Element element = (Element) node;
			element.addAttribute(attributeName, attributeValue);
		}
	}
	
	/**
	 * 为指定路径的多个节点添加属性
	 * @param xpath 路径
	 * @param attributeName 属性名
	 * @param attributeValue 属性值
	 * @throws GeneralException
	 */
	protected void addMultipleAttribute(String xpath,String attributeName,String attributeValue) throws GeneralException{
		List<Node> elmentList = getNodes(xpath);
		for(Node node : elmentList) {
			if (!(node instanceof Element)) {
				throw new GeneralException("xml解析错误!");
			}
			Element element = (Element)node;
			List<Node> attributeList = element.selectNodes("./@"+attributeName);
			if(attributeList.size() == 0 || attributeList.size() == 1) {
				element.addAttribute(attributeName, attributeValue);
			}else{
				throw new GeneralException("xml解析错误!");
			}
		}
	}
	
	protected void removeAttribute(Element element,String attributeName){
		Node attribute = element.selectSingleNode("./@"+attributeName);
		if(attribute!=null){
			attribute.detach();
		}
	}
	
	/**
	 * 添加单个节点
	 * @param xpath
	 * @param newElement
	 * @throws GeneralException
	 */
	protected void addSingeElement(String xpath,Element newElement) throws GeneralException{
		List<Node> elmentList = getNodes(xpath);
		if (elmentList.size() > 1) {
			throw new GeneralException("xml解析错误!");
		}else if(elmentList.size()==1){
			Element element = (Element) elmentList.get(0);
			element.add(newElement.createCopy());
		}
	}

	/**
	 * 添加多个节点
	 * @param xpath
	 * @param newElement
	 * @throws GeneralException
	 */
	protected void addMultipleElement(String xpath,Element newElement) throws GeneralException{
		List<Node> elmentList = getNodes(xpath);
		for(Node node : elmentList){
			Element element = (Element)node;
			element.add(newElement.createCopy());
		}
	}

	/**
	 * 将element的文本转化为子节点
	 * @param xpath
	 * @throws GeneralException
	 */
	public void convertTextToElement(String xpath,String xml) throws GeneralException{
		List<Node> elmentList = getNodes(xpath);
		if (elmentList.size() > 1) {
			throw new GeneralException("xml解析错误!");
		}else if(elmentList.size()==1){
			Node node = elmentList.get(0);
			Element element = (Element) node;
//			String xml = StringEscapeUtils.unescapeXml(element.getText());
			element.clearContent();
			XmlParse xp = new XmlParse(xml);
			element.add(xp.getRootElement().detach());
		}
	}
	
	/**
	 * 格式化xml对象，并返回字符串
	 * @return
	 */
	public String asPrettyXML(){
		final OutputFormat format = OutputFormat.createPrettyPrint();
		StringWriter sw = new StringWriter();
		final XMLWriter writer = new XMLWriter(sw, format);
		try {
			writer.write(rootElement);
			return sw.toString();
		} catch (IOException e) {
			return rootElement.asXML();
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//静态方法

	public String asCompactXML(){
		final OutputFormat format = OutputFormat.createCompactFormat();
		StringWriter sw = new StringWriter();
		final XMLWriter writer = new XMLWriter(sw, format);
		try {
			writer.write(rootElement);
			return sw.toString();
		} catch (IOException e) {
			return rootElement.asXML();
		}

	}

}
