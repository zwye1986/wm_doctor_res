package com.pinde.core.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.StringWriter;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlUtil {

	private static XStream marshallerXs;

//	private static XStream xmlXs = new XStream();
	
//	private static XStream jsonXs = new XStream(new JettisonMappedXmlDriver());

	static {

//		jsonXs.setMode(XStream.NO_REFERENCES);
		marshallerXs = new XStream(new DomDriver());
	}

	public static String marshaller(Object obj) {
		return marshallerXs.toXML(obj);
	}

	public static Object unmarshaller(String xml) {
		return marshallerXs.fromXML(xml);
	}
	
	public static <T> T unmarshallerToT(String xml){
		return (T)unmarshaller(xml);
	}
	
//	public static void toXmlAlias(Class cls){
//		String rootName = cls.getSimpleName();
//		String firstStr = rootName.substring(0, 1);
//		rootName = rootName.replaceFirst(firstStr, firstStr.toLowerCase());
//		xmlXs.alias(rootName, cls);
//	}

//	public static String toXml(Object obj){
//		_aliasTreeClass(obj.getClass());
//		return xmlXs.toXML(obj);
//	}
	
//	private static DefaultXMLBeanInfoRegistry xmlBeanInfoRegistry = new  DefaultXMLBeanInfoRegistry();
	
//	public static String toXml(Object obj){
//		StringWriter outputWriter = new StringWriter(); 
//		BeanWriter beanWriter = new BeanWriter(outputWriter);
//		beanWriter.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
//		beanWriter.getXMLIntrospector().setRegistry(xmlBeanInfoRegistry);        
////		beanWriter.getXMLIntrospector().setAttributeNameMapper(new HyphenatedNameMapper());
//		beanWriter.getXMLIntrospector().setElementNameMapper(new DecapitalizeNameMapper());
//        beanWriter.getBindingConfiguration().setMapIDs(false);
//        beanWriter.setEndTagForEmptyElement(false);
//        beanWriter.enablePrettyPrint();
//		try {
//	        beanWriter.write(obj);
//			String xml = outputWriter.toString();
//			outputWriter.close();
//			return xml;
//		} catch (Exception e) {
//		}
//		return "";
//	}
	
//	private static Map<String, Class> xmlXsAliasMap = new HashMap<String, Class>();
	
//	private static void _aliasTreeClass(Class cls){
//		//退出条件
//		if (cls.getPackage().getName().equals("java.lang")) {   
//		    return;   
//		}   
//		if (!cls.getPackage().getName().startsWith("com.just")) {   
//			return;   
//		}
//		//注册类本身
//		{
//			String rootName = cls.getSimpleName();
//			String firstStr = rootName.substring(0, 1);
//			rootName = rootName.replaceFirst(firstStr, firstStr.toLowerCase());
//			if(!xmlXsAliasMap.containsKey(rootName)){
//				xmlXs.alias(rootName, cls);
//				xmlXsAliasMap.put(rootName, cls);			
//			}else{
//				 return; 
//			}
//		}
//		//注册类属性
//		Field[] fields = cls.getDeclaredFields();		
//		for(int i=0;fields!=null&&i<fields.length;i++){
//			Field field = fields[i];
//			Class fieldType = field.getType(); 
//			//如果是基本类型，跳过   
//			if (fieldType.isPrimitive()){   
//				continue;   
//			}
//			Type gType = field.getGenericType();
//			if(gType instanceof ParameterizedType){
//				ParameterizedType pType = (ParameterizedType)gType;
//                Type[] tArgs = pType.getActualTypeArguments();
//                for (int j = 0; tArgs!=null&&j < tArgs.length; j++) {
//                	Class pcls = (Class)tArgs[j];
//    				_aliasTreeClass(pcls);
//                }
//			}else{
//				Class fcls = field.getGenericType().getClass();
//				_aliasTreeClass(fcls);
//			}
//		}
//	}
	
//	public static Object fromXml(String xml,Class cls) {
//		String rootName = cls.getSimpleName();
//		String firstStr = rootName.substring(0, 1);
//		rootName = rootName.replaceFirst(firstStr, firstStr.toLowerCase());
//		xmlXs.alias(rootName, cls);
//		return xmlXs.fromXML(xml);
//	}

//	public static String toJson(Object obj) {
//		String rootName = obj.getClass().getSimpleName();
//		String firstStr = rootName.substring(0, 1);
//		rootName = rootName.replaceFirst(firstStr, firstStr.toLowerCase());
//		jsonXs.alias(rootName, obj.getClass());
//		return jsonXs.toXML(obj);
//	}

	public static String prettyFormat(String unformattedXml) {
		if (StringUtil.isBlank(unformattedXml)) {
			throw new RuntimeException("xml was null or blank in prettyPrint()");
		}
		final StringWriter sw;
		try {
			final OutputFormat format = OutputFormat.createPrettyPrint();
			final Document document = DocumentHelper.parseText(unformattedXml);
			sw = new StringWriter();
			final XMLWriter writer = new XMLWriter(sw, format);
			writer.write(document);
		} catch (Exception e) {
			throw new RuntimeException("Error pretty printing xml:\n" + unformattedXml, e);
		}
		return sw.toString();
	}
	


	public static String compactFormat(String incompactXml) {
		if (StringUtil.isBlank(incompactXml)) {
			throw new RuntimeException("xml was null or blank in prettyPrint()");
		}
		final StringWriter sw;
		try {
			final OutputFormat format = OutputFormat.createCompactFormat();
			final Document document = DocumentHelper.parseText(incompactXml);
			sw = new StringWriter();
			final XMLWriter writer = new XMLWriter(sw, format);
			writer.write(document);
		} catch (Exception e) {
			throw new RuntimeException("Error pretty printing xml:\n" + incompactXml, e);
		}
		return sw.toString();
	}

	public static String wipeOffNotAccord(String instr) {
		StringBuffer out = new StringBuffer();
		char current;
		if (instr == null || ("".equals(instr))) return "";
		for (int i = 0; i < instr.length(); i++) {
			current = instr.charAt(i);
			if(current == 0x1e){
				continue;
			} else if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current > 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
			|| ((current >= 0x10000) && (current <= 0x10FFFF))||(current < 0x0)
					) {
				out.append(current);
			}
		}
		return out.toString().trim();
	}

	public static void setNamespace(Element elem, Namespace ns) {
		elem.setQName(QName.get(elem.getName(), ns, elem.getQualifiedName()));
	}

//	public static void main(String[] args) {
//		CpuInfo cpuInfo = new CpuInfo();
//		System.err.println(toJson(cpuInfo));
//	}
	
	public static void removeNamespaces(Element elem) {
		setNamespaces(elem, Namespace.NO_NAMESPACE);
	}

	public static void setNamespaces(Element elem, Namespace ns) {
		setNamespace(elem, ns);
		setNamespaces(elem.content(), ns);
	}

	public static void setNamespaces(List<Node> list, Namespace ns) {
		for (Node node : list) {
			if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
				((Attribute) node).setNamespace(ns);
			}
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				setNamespaces((Element) node, ns);
			}
		}
	}
	
	/**
	 * 根据Map对象中的key-value对 生成xml字符串 , key对应节点名称  , value对应节点的文本  ,
	 * 此功能现在还是比较简单，如果遇到复杂的数据结构 就需要更改
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public static String createXmlStr(Map<String , Object> dataMap) throws Exception{
		Document document = DocumentHelper.createDocument();

		Element rootElement = document.addElement("projInfo");

		//遍历map
		Set<Entry<String , Object>> entrySet = dataMap.entrySet();
		Iterator<Entry<String , Object>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String , Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			Element element = rootElement.addElement(key);
			element.setText(String.valueOf(value).trim());

		}
		return document.asXML();
	}
	
	/**
	 * 将xml字符串转化为map集合
	 * @param xml
	 * @return
	 */
	public static Map<String , String> parseXmlStr(String xml){
		Map<String , String> map = new HashMap<String , String>();
		Document doc = null;
		try{
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Iterator iterator = root.elementIterator();
			while(iterator.hasNext()){
				Element element = (Element)iterator.next();
				map.put(element.getName(), element.getTextTrim());
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

	public String cleanInvalidXmlChars(String text) {
		// From xml spec valid chars:
		// #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
		// any Unicode character, excluding the surrogate blocks, FFFE, and FFFF.
		// [^\\x09\\x0A\\x0D\\x20-\\xD7EF\\xE000-\\xFFFD\\x10000-\\x10FFFF]
		Pattern pt = Pattern.compile("[^\\x09\\x0A\\x0D\\x20-\\xD7EF\\xE000-\\xFFFD\\x10000-x10FFFF]");
		Matcher mat = pt.matcher(text);
		return mat.replaceAll("");
	}
	
	
	/*
	public static void main(String[] args) throws Exception{
		Map<String , Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("projBasicInfo_zhcnName", "你好");
		dataMap.put("projBasicInfo_usName", "hello");
		dataMap.put("projIntro_projIntro1", "xxxxxx");
		dataMap.put("projGeneralCondition", "yyyyyy");
		String xml = createXmlStr(dataMap);
		System.out.println(xml);
	}*/
	
	
	
	
}
