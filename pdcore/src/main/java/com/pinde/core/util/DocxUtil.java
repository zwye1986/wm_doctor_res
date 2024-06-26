package com.pinde.core.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.*;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;

import java.io.*;
import java.util.*;

public final class DocxUtil {
	
	private static String getTemplate(File templateFile)throws Exception{
		BufferedReader bufReader=new BufferedReader(new InputStreamReader(new FileInputStream(templateFile),"UTF-8"));
		String line = null;
		StringBuffer template = new StringBuffer();		
		while ((line = bufReader.readLine()) != null)
		{
			 template.append(line);
		}
		bufReader.close();
		return template.toString();
	}

	private static StringWriter evaluate(File templateFile,Map templateParams) throws Exception {
		String template = getTemplate(templateFile);
		VelocityEngine ve = new VelocityEngine();
		Properties properties = new Properties();
		properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		ve.init(properties);

		templateParams.put("DateTool", new ComparisonDateTool());
		templateParams.put("MathTool", new MathTool());
		templateParams.put("NumberTool", new NumberTool());
		templateParams.put("EscapeTool", new EscapeTool());
		templateParams.put("DisplayTool", new DisplayTool());
		templateParams.put("ConversionTool", new ConversionTool());
//		templateParams.put("LoopTool", new LoopTool());

		VelocityContext context = new VelocityContext(templateParams);
		StringWriter writer = new StringWriter();
		Velocity.init();
		Velocity.evaluate(context, writer, "log tag name", template);
		return writer;
	}

	public static void convert(File templateFile,String outputPath, Map<String , Object> templateParams) throws Exception {
		if (templateFile != null && outputPath!=null) {
//			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File("D:\\template.docx"));
			MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
			StringWriter writer = evaluate(templateFile,templateParams);
			Object obj = XmlUtils.unmarshalString(writer.toString());
			documentPart.setJaxbElement((Document) obj);
			wordMLPackage.addTargetPart(documentPart);
			wordMLPackage.save(new java.io.File(outputPath));
			writer.flush();
			writer.close();
		}
	}

	public static void main(String[] args) {
		Map<String, Object> projInfoMap = new HashMap<String, Object>();
		projInfoMap.put("projGeneral_projName", "XXX");
		projInfoMap.put("partnar", "i am 张杰.");
		List<Map<String, Object>> lisItemList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<10;i++){
			Map<String, Object> lisItem1 = new HashMap<String, Object>();
			lisItem1.put("id", 1);
			lisItem1.put("value", "中文");
			lisItemList.add(lisItem1);
			
		}
		projInfoMap.put("lisItemList", lisItemList);

		File sourceFile = new File("D:\\template.xml");
		try {
			DocxUtil.convert(sourceFile, "D:\\"+DateUtil.getCurrDateTime()+".docx",projInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
