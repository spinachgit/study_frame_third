package com.spinach.frame.PD4ML;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

public class pd4mlUtil {
	public static void main(String[] args) throws Exception {
		File pdfFile = new File("D:/pdf/index.pdf");
		htmltopdf(pdfFile, "D:/pdf/index.html");
		newPdfByContent("aaa", "D:/logs/temp.pdf");
		newPdfByHtmlFile("D:/logs/temp.pdf", "D:/logs/temp.pdf");
	}

	/**
	 * pdf 文件生成
	 * @param content 生成的文件内容
	 * @param outputPDFFile 输出文件路径
	 */
	public static void newPdfByContent(String content, String outputPDFFile) {
		StringReader strReader = null;
		FileOutputStream fos = null;
		try {
			strReader = new StringReader(content);
			fos = new FileOutputStream(outputPDFFile);
			PD4ML pd4ml = new PD4ML();
			pd4ml.setPageInsets(new Insets(25, 10, 10, 10));
			// pd4ml.setHtmlWidth(600);
			// pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
			pd4ml.useTTF("java:fonts", true);
			// if("0".equals(type)){
			// pd4ml.setDefaultTTFs("SimSun", "Arial", "Courier New");
			pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
			// }
			pd4ml.enableDebugInfo();
			
			PD4PageMark footer = new PD4PageMark();
			footer.setHtmlTemplate("<div style=\"text-align: center;\">" + "<hr style=\"background-color: rgb(91, 239, 252);\"/>" + "<font><strong><i>page $[page] of $[total]</i></strong></font>" + "</div>");
			footer.setAreaHeight(-1);
			pd4ml.setPageFooter(footer);
			System.out.println("生成开始 ......" + outputPDFFile);
			pd4ml.render(strReader, fos);
			System.out.println("生成结束 ......" + outputPDFFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				// throw new RuntimeException(e);
			}
			try {
				if (strReader != null)
					strReader.close();
			} catch (Exception e) {

			}

		}
		System.out.println(" newPdf结束");
	}

	/**
	 * pdf 文件生成
	 * 
	 * @param inputHtmlFile HTML文件路径。
	 * @param outputPDFFile 导出文件路径
	 */
	public static void newPdfByHtmlFile(String inputHtmlFile, String outputPDFFile) {
		System.out.println(" newPdfByFile开始" + outputPDFFile);
		FileOutputStream fos = null;
		File file = new File(inputHtmlFile);
		if (!file.exists()) {
			System.out.println(inputHtmlFile + " doesn't exist!");
			return;
		}
		InputStreamReader inputStream = null;
		try {
			inputStream = new InputStreamReader(new FileInputStream(inputHtmlFile), "UTF-8");
			System.out.println("................ + outputPDFFile =" + outputPDFFile);
			fos = new FileOutputStream(outputPDFFile);
			PD4ML pd4ml = new PD4ML();
			pd4ml.setPageInsets(new Insets(25, 10, 10, 10));
			pd4ml.useTTF("java:fonts", true);
			pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
			pd4ml.enableDebugInfo();
			PD4PageMark footer = new PD4PageMark();
			footer.setHtmlTemplate(
					"<div style=\"text-align: center;\">" + "<hr style=\"background-color: rgb(91, 239, 252);\"/>" + "<font><strong><i>page $[page] of $[total]</i></strong></font>" + "</div>");
			footer.setAreaHeight(-1);
			pd4ml.setPageFooter(footer);
			System.out.println("生成开始 ......" + outputPDFFile);
			pd4ml.render(inputStream, fos);
			System.out.println("生成结束 ......" + outputPDFFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				// throw new RuntimeException(e);
			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {

			}

		}
		System.out.println("newPdfByFile结束");
	}
	
	/**
	 * html文件转PDF  
	 * @date:2017年12月6日下午7:06:03
	 * @param outputPDFFile
	 * @param inputHTMLFileName
	 * @throws Exception
	 */
	public static void htmltopdf(File outputPDFFile, String inputHTMLFileName) throws Exception {
		FileOutputStream fos = new FileOutputStream(outputPDFFile);
		PD4ML pd4ml = new PD4ML();
		
		pd4ml.setPageInsets(new Insets(40,30,30,40));
		pd4ml.setHtmlWidth(960);
		PD4PageMark p = new PD4PageMark();
		p.setHtmlTemplate("this is template");
		p.setInitialPageNumber(5);
		//p.setPageNumberAlignment(11);
		pd4ml.setPageHeader(p);
		pd4ml.setPageSize(PD4Constants.A4);
		//pd4ml.useTTF("java:fonts", true);
		//pd4ml.setDefaultTTFs("KaiTi_GB2312", "KaiTi_GB2312", "KaiTi_GB2312");
		
		pd4ml.useTTF("java:fonts", true);
		pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
		
		pd4ml.enableDebugInfo();
		pd4ml.render("file:" + inputHTMLFileName,fos);
	}
}
