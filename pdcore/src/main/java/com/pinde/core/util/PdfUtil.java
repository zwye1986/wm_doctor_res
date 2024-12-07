package com.pinde.core.util;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class PdfUtil {
	/*wps 与 word 参数不同*/
	public final static String WORDSERVER_STRING="KWPS.Application";//"Word.Application"
	public final static String PPTSERVER_STRING="KWPP.Application";//"PowerPoint.Application"
	public final static String EXECLSERVER_STRING="KET.Application";//"Excel.Application"

	private static final int wdFormatPDF = 17;
	private static final int xlTypePDF = 0;
	private static final int ppSaveAsPDF = 32;

	private static Logger logger = LoggerFactory.getLogger(PdfUtil.class);
	/**
	 * pdf格式转换为swf格式
	 * @param source	源文件地址
	 * @param target	目标文件地址
	 * @return
	 */
	private boolean pdf2Swf(String source, String target) {
		try {
			String swftoolspath = "D:\\SWFTools\\";
//			String command = swftoolspath + "pdf2swf.exe  -t \""
//					+ source
//					+ "\" -o  \""
//					+ target
//					+ "\" -s flashversion=9 -s languagedir=" + swftoolspath + "xpdf/xpdf-chinese-simplified";
			 // 调用pdf2swf命令进行转换
	        String command = swftoolspath
	                + "/pdf2swf.exe  -t \""
	                + source
	                + "\" -o  \""
	                + target
	                + "\" -s flashversion=9 -s languagedir="+swftoolspath+"xpdf-chinese-simplified ";

			//String command = swftoolspath + "pdf2swf " + source + " -o  " + target+" -f -T9";
			//执行cmd命令
			Process process = Runtime.getRuntime().exec(command);
			final InputStream is1 = process.getInputStream();
	        new Thread(new Runnable() {
	            public void run() {
	                BufferedReader br = new BufferedReader(new InputStreamReader(
	                        is1));
	                try {
	                    while (br.readLine() != null)
	                        ;
	                } catch (IOException e) {
                        logger.error("", e);
	                }
	            }
	        }).start(); // 启动单独的线程来清空process.getInputStream()的缓冲区
	        
	        InputStream is2 = process.getErrorStream();
	        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
	        // 保存输出结果流
	        StringBuilder buf = new StringBuilder();
	        String line = null;
	        while ((line = br2.readLine()) != null)
	            // 循环等待ffmpeg进程结束
	            buf.append(line);
	        while (br2.readLine() != null)
	            ;
	        try {
	            process.waitFor();
	        } catch (InterruptedException e) {
                logger.error("", e);
	        }

			System.out.println("ffmpeg输出内容为：" + buf.toString());
		} catch (Exception e) {
            logger.error("", e);
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		PdfUtil tt = new PdfUtil();
		String source="D://SWFTools//pdf//111.pdf";//源文件地址
		String target="D://SWFTools//swf//111.swf";//目标文件地址
		boolean aa = tt.pdf2Swf(source, target);
		if(aa==true){
			System.out.println("tt");
		}else{	
			System.out.println("nn");
		}
		
	}

	public static boolean convert2PDF(String inputFile, String pdfFile) {
		String suffix = getFileSufix(inputFile);
		File file = new File(inputFile);
		if (!file.exists()) {
			return false;
		}
		if (suffix.equals("pdf")) {
			return false;
		}
		if (suffix.equals("doc") || suffix.equals("docx")
				|| suffix.equals("txt")) {
			return word2PDF(inputFile, pdfFile);
		} else if (suffix.equals("ppt") || suffix.equals("pptx")) {
			return ppt2PDF(inputFile, pdfFile);
		} else if (suffix.equals("xls") || suffix.equals("xlsx")) {
			return excel2PDF(inputFile, pdfFile);
		} else {
			return false;
		}
	}
	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}
	// word转换为pdf
	public static boolean word2PDF(String inputFile, String pdfFile) {
		try {
			// 打开word应用程序
			ActiveXComponent app = new ActiveXComponent(WORDSERVER_STRING);
			// 设置word不可见
			app.setProperty("Visible", false);
			// 获得word中所有打开的文档,返回Documents对象
			Dispatch docs = app.getProperty("Documents").toDispatch();
			// 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
			Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true)
					.toDispatch();
			// 调用Document对象的SaveAs方法，将文档保存为pdf格式
            /*
             * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF
             * //word保存为pdf格式宏，值为17 );
             */
			Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17
			// 关闭文档
			Dispatch.call(doc, "Close", false);
			// 关闭word应用程序
			app.invoke("Quit", 0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	// excel转换为pdf
	public static boolean excel2PDF(String inputFile, String pdfFile) {
		try {
			ActiveXComponent app = new ActiveXComponent(EXECLSERVER_STRING);
			app.setProperty("Visible", false);
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.call(excels, "Open", inputFile, false,
					true).toDispatch();
			Dispatch.call(excel, "ExportAsFixedFormat", xlTypePDF, pdfFile);
			Dispatch.call(excel, "Close", false);
			app.invoke("Quit");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	// ppt转换为pdf
	public static boolean ppt2PDF(String inputFile, String pdfFile) {
		try {
			ActiveXComponent app = new ActiveXComponent(PPTSERVER_STRING);
			// app.setProperty("Visible", msofalse);
			Dispatch ppts = app.getProperty("Presentations").toDispatch();

			Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true,// ReadOnly
					true,// Untitled指定文件是否有标题
					false// WithWindow指定文件是否可见
			).toDispatch();

			Dispatch.call(ppt, "SaveAs", pdfFile, ppSaveAsPDF);

			Dispatch.call(ppt, "Close");

			app.invoke("Quit");
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
