package com.spinach.frame.PD4ML;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import com.spinach.frame.utils.FileUtils;
import com.spinach.frame.utils.StringUtils;

public class pd4mlUtil {
	public static String basePath = "src/test/resources/pdf";
	public static String HTML = "src/main/webapp/resources/itextpdf/xml/movies.html";
	public static void main(String[] args) throws Exception {
//		htmlToPdf(basePath+"/movies.html",new File(basePath+"/result/out05.pdf"),basePath+"/style1.css");
//		htmlToPdf(basePath+"/movies.html",new File(basePath+"/result/out06.pdf"),basePath+"/style2.css");
		htmlToPdf(basePath+"/movies.html",new File(basePath+"/result/out07aaa.pdf"),basePath+"/style3.css");
		//htmlToPdf(basePath+"/movies.html",new File(basePath+"/result/out08.pdf"),basePath+"/style4.css");
		//contentToPdf("aaa", basePath+"/result/out01.pdf",basePath+"/music.css");
		//htmlToPdf(basePath+"/result01.html",basePath+"/result/out03.pdf",basePath+"/music.css");
		//htmlToPdf(basePath+"/result01.html",new File(basePath+"/result/out033.pdf"),basePath+"/music.css");
		System.out.println("----操作完成------");
	}

	/**
	 * 获得默认的pd4ml对象
	 * @author:whh
	 * @date:2018年3月1日下午3:26:21
	 * @return
	 * @throws FileNotFoundException
	 */
	private static PD4ML getDefaultPD4ML(String cssPath) throws FileNotFoundException {
		PD4ML pd4ml = new PD4ML();
		/** 增加外部样式文件内容。 注意：不是文件路径 **/
		//pd4ml.addStyle("table{color:red;}", true); //外部文件内容。
		if(StringUtils.isNotEmpty(cssPath)){
			pd4ml.addStyle(FileUtils.readFile(cssPath), true);
		}
		
		pd4ml.setPageInsets(new Insets(5, 5, 5, 5)); //new Insets(40, 30, 30, 40)
		pd4ml.setHtmlWidth(PD4Constants.A4.width+200); // set frame width of "virtual web browser" 
		//反方向 
		//pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4)); 
		pd4ml.setPageSize(PD4Constants.A4); 
		
		/**
		 * 字体文件设置
		 */
		//pd4ml.useTTF("fonts", true);  
		//pd4ml.useTTF("/Library/Fonts", true); //指定系统字体文件夹
		//pd4ml.useTTF("/Library/Fonts/Microsoft", true); //指定系统字体文件夹
		pd4ml.useTTF("src/main/resources/pd4ml_fonts/fonts", true); //指定系统字体文件夹
		//pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
		//pd4ml.setDefaultTTFs("Monacow", "Arial", "Courier New"); //Georgia Microsoft Yahei
		pd4ml.setDefaultTTFs("Calibri_Bold_Italic", "Songti", "Monacow"); //StXingKa
		
		pd4ml.enableDebugInfo();
		/** 生成文件目录 **/
		pd4ml.generateOutlines(true);

		/** 生成对应的页眉页脚 **/
		////AAAAAAAAAAAAAAAAAAAAA
		PD4PageMark header = new PD4PageMark();
		header.setHtmlTemplate("<div style=\"text-align: center;\">"
				+ "<font style=\"color:#79a73a\"><strong>作者：whh qq:1043204960</strong></font><hr /></div>");
		header.setAreaHeight(30); //设置标头的高度
		pd4ml.setPageHeader(header);
			
		PD4PageMark footer = new PD4PageMark();
		footer.setHtmlTemplate("<div style=\"text-align: center;\"><hr style=\"background-color:#79a73a;\"/>"
				+ "<font><strong style=\"color:red\"><i>page $[page] of $[total]</i></strong></font></div>");
		footer.setAreaHeight(-1);
		pd4ml.setPageFooter(footer);
		pd4ml.setPageSize(PD4Constants.A4);
		
		return pd4ml;
	}
	/**
	 * pdf 文件生成
	 * @param content 文件内容：可以是字符串，也可以是HTML文件内容
	 * @param outputPDFFile 输出文件路径
	 */
	public static void contentToPdf(String content, String outputPDFFile,String cssPath) {
		StringReader strReader = null;
		FileOutputStream fos = null;
		try {
			strReader = new StringReader(content);
			fos = new FileOutputStream(outputPDFFile);
			PD4ML pd4ml = getDefaultPD4ML(cssPath);
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
	 * html文件转PDF  
	 * @date:2017年12月6日下午7:06:03
	 * @param inputHtmlPath
	 * @param outputPDFFile
	 * @throws Exception
	 */
	public static void htmlToPdf(String inputHtmlPath,File outputPDFFile,String cssPath) throws Exception {
		/** 输入文件不存在，则抛出错误 **/
		File file = new File(inputHtmlPath);
		if (!file.exists()) {
			System.out.println(inputHtmlPath + " doesn't exist!");
			throw new FileNotFoundException("文件没有找到！"+inputHtmlPath);
		}
		/** 输出文件一定要存在 **/
		if(!outputPDFFile.exists()){
			outputPDFFile.getParentFile().mkdirs();
		}
		System.out.println("生成开始 ......" + outputPDFFile.getPath());
		FileOutputStream fos = new FileOutputStream(outputPDFFile);
		PD4ML pd4ml = getDefaultPD4ML(cssPath);
		pd4ml.render("file:" + inputHtmlPath, fos);
		System.out.println("生成结束 ......" + outputPDFFile.getPath());
	}
	
	/**
	 * pdf 文件生成
	 * 
	 * @param inputHtmlPath HTML文件路径。
	 * @param outputPDFPath 导出文件路径
	 * @throws FileNotFoundException 
	 */
	public static void htmlToPdf(String inputHtmlPath, String outputPDFPath,String cssPath) throws FileNotFoundException {
		System.out.println(" newPdfByFile开始" + outputPDFPath);
		FileOutputStream fos = null;
		/** 输入文件不存在，则抛出错误 **/
		File file = new File(inputHtmlPath);
		if (!file.exists()) {
			System.out.println(inputHtmlPath + " doesn't exist!");
			throw new FileNotFoundException("文件没有找到！"+inputHtmlPath);
		}
		/** 输出文件一定要存在 **/
		File outFile = new File(outputPDFPath);
		if(!outFile.exists()){
			outFile.getParentFile().mkdirs();
		}
		InputStreamReader inputStream = null;
		try {
			inputStream = new InputStreamReader(new FileInputStream(inputHtmlPath), "UTF-8");
			System.out.println("................ + outputPDFFile =" + outputPDFPath);
			fos = new FileOutputStream(outputPDFPath);
			PD4ML pd4ml = getDefaultPD4ML(cssPath);
			
			System.out.println("生成开始 ......" + outputPDFPath);
			pd4ml.render(inputStream, fos);
			System.out.println("生成结束 ......" + outputPDFPath);
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
}
