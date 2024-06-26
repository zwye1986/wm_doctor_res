package com.pinde.core.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.*;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

public class VelocityUtil {
	
	public static String evaluate(String template,Map templateParams) throws Exception {
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
		return writer.toString();
	}
}
