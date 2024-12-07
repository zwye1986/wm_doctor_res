package com.pinde.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class JaxbUtil {
    private static Logger logger = LoggerFactory.getLogger(JaxbUtil.class);


	private final static String ENCODING = "UTF-8";

	private JaxbUtil() {
	}

	public static String marshaller(Object obj) throws JAXBException, IOException {

		String xml = "";
		String pkgName = obj.getClass().getPackage().getName();
		ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();

		try {

			JAXBContext jaxbCtx = JAXBContext.newInstance(pkgName);
			Marshaller marshaller = jaxbCtx.createMarshaller();
			// Perform marshal
			marshaller.marshal(obj, byteArrayOS);

			byte[] bytes = byteArrayOS.toByteArray();
			xml = new String(bytes, ENCODING);

		} finally {
			if (byteArrayOS != null) {
				byteArrayOS.close();
			}
		}

		return xml;

	}

	public static Object unmarshaller(String xml, Class<?> cls) throws JAXBException, UnsupportedEncodingException {

		// check validness.
		if (StringUtils.isBlank(xml) == true) {
			throw new JAXBException("The input xml is blank.");
		}

		// Build BO object from XML column value.
		byte[] bytes = xml.getBytes(ENCODING);
		ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);

		String pkgName = cls.getPackage().getName();
		JAXBContext jaxbCtx = JAXBContext.newInstance(pkgName);
		Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
		StreamSource streamSource = new StreamSource();
		streamSource.setInputStream(byteArrayIS);
		Object obj = unmarshaller.unmarshal(streamSource);

		return obj;

	}
	
	public static String convertToXml(Object obj) {  
        return convertToXml(obj, "UTF-8");  
    }  
	
	 /** 
     * JavaBean转换成xml 
     * @param obj 
     * @param encoding  
     * @return  
     */  
    public static String convertToXml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {
            logger.error("", e);
        }  
  
        return result;  
    }  
    
    /** 
     * xml转换成JavaBean 
     * @param xml 
     * @param c 
     * @return 
     */
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {
            logger.error("", e);
        }  
  
        return t;  
    }   

}
