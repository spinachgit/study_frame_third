package com.spinach.frame.pdfbox;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import com.spinach.jdk.utils.FileUtils;

/**
 * <p>
 *  <dependency>
			<groupId>org.apache.pdfbox</groupId>
			<artifactId>pdfbox</artifactId>
			<version>2.0.7</version>
			<!-- <version>1.8.7</version> -->
		</dependency>
 * </p>
 * @author wanghuihui
 * @date 2017年9月19日下午2:40:33
 */
public class PDFBoxNewDemo {
	public static void main(String[] args) throws Exception {
		String path = "D:/logs/1505119837830.pdf";
		String dest = "D:/logs";
		String path2 = "D:\\logs\\day.pdf";
		PDFBoxNewDemo.writeToPdf("hello world", path2);
		//PDFBoxNewDemo.getText(path);
		//PDFBoxNewDemo.readPdfToImage(path, dest);
		//PDFBoxNewDemo.addOutlineToPDF(path, path2);
	}
	
	public static void mergePdfs(String folder,String mergeFileName) throws Exception {
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		String[] filesInFolder = getFiles(folder);
		for (int i = 0; i < filesInFolder.length; i++)
		{
			mergePdf.addSource(folder + File.separator + filesInFolder[i]);
		}
		mergePdf.setDestinationFileName(folder + File.separator + mergeFileName);
		mergePdf.mergeDocuments();
		System.out.print("合并完成__LLL丶禾羊__博客");
	}
	public static String[] getFiles(String folder) throws IOException {
		File _folder = new File(folder);
		String[] filesInFolder;
		if (_folder.isDirectory()) {
			filesInFolder = _folder.list();
			return filesInFolder;
		} else {
			throw new IOException("Path is not a directory");
		}
	}
	
	private static void writeToPdf(String contentStr,String pdfPath) throws IOException {
		FileInputStream inputs = new FileInputStream("D:\\pdf\\index.txt");
		String temp = FileUtils.readStream(inputs);
		System.out.println(temp);
		try {
			File file = new File(pdfPath);
			if(!file.exists()){
				file.createNewFile();
			}
			PDDocument document = new PDDocument();
			PDPage pag1 = new PDPage();
			document.addPage(pag1);
			
			PDPageContentStream content = new PDPageContentStream(document, pag1);
			//PDFont font = PDTrueTypeFont.load(document, new File("C:\\Windows\\Fonts\\simfang.ttf"), new Encoding("PCM_SIGNED"));
			//PDType0Font font1 = PDType0Font.load(document, new File(dir + "LiberationSans-Regular.ttf"));
			
			PDType0Font font = PDType0Font.load(document, new File("C:\\Windows\\Fonts\\SIMSUNB.TTF"));
			//PDFont font = PDType1Font.HELVETICA_BOLD;
			content.setFont(font, 12);
			content.beginText();
			content.newLineAtOffset(80, 750);
			content.showText(temp);
			content.setNonStrokingColor(Color.blue);
			content.endText();
			content.close();
			document.save(pdfPath);
			System.out.println("-----写入PDF数据完成-----------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印pdf文档文本内容
	 * @param strFile pdf文件
	 * @throws Exception
	 */
	private static void getText(String strFile) throws Exception{
		boolean sort = false;
		int startPage = 1;
        int endPage = Integer.MAX_VALUE;
		
		InputStream inputStream = null;
		Writer outWriter = null;
		PDFTextStripper textStripper = null;
		PDDocument document = null;
		try {
			inputStream = new FileInputStream(strFile);
			outWriter = new OutputStreamWriter( System.out );
			
			textStripper = new PDFTextStripper();
			document = PDDocument.load(inputStream);
			
			textStripper.setSortByPosition( sort );
			textStripper.setStartPage( startPage );
			textStripper.setEndPage( endPage );
            
			textStripper.writeText(document, outWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			inputStream.close();
			outWriter.flush();
			outWriter.close();
		}
	}
	
	/**
	 * <p>
	 *  读取PDF转IMAGE
	 * </p>
	 * @date 2017年9月19日下午2:19:43
	 * @param path
	 * @param dest
	 * @throws IOException
	 */
	public static void readPdfToImage(String path,String dest) throws IOException {
	    PDDocument doc = null;
		try {
			doc = PDDocument.load(new File(path));
			PDFRenderer render = new PDFRenderer(doc);
			int count = doc.getNumberOfPages();
			for (int i = 0; i < count; i++) {
				//设置图片的分辨率
				BufferedImage image = render.renderImageWithDPI(i, 296);
				//如果是PNG图片想要背景透明的话使用下面这个
				//BufferedImage image = render.renderImageWithDPI(i, 296, ImageType.ARGB);
				ImageIO.write(image, "PNG", new File(dest + File.separator + i + ".png"));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(doc != null){
				doc.close();
			}
		}
	}
	
	public static String addOutlineToPDF(String filePath, String outputFilePath) throws Exception {
        PDDocument document = null;
        String outFilePath = "";
        try {
            document = PDDocument.load(new File(filePath));
            if (document.isEncrypted()) {
                System.err.println("Error: Cannot add bookmarks to encrypted document.");
                System.exit(1);
            }
            PDFTextStripper stripper = new PDFTextStripper();
            PDDocumentOutline outline = new PDDocumentOutline();
            document.getDocumentCatalog().setDocumentOutline(outline);
            //List pages = document.getDocumentCatalog().getAllPages(); //老版本写法：比如版本1.8.7
            //List<PDPage> pages = document.getPages();
            //PDPageTree pages = document.getPages();
            PDPageTree pages = document.getDocumentCatalog().getPages();
            Iterator<PDPage> iterator = pages.iterator();
            
            boolean oriFlag = false;
            boolean arrFlag = false;
            boolean altFlag = false;
            boolean infoFlag = false;
            //for (int i = 0; i < pages.size(); i++) {
            int i=0;
            for (PDPage pdPage : pages) {
                PDPage page = (PDPage) pages.get(i);
                stripper.setStartPage(i + 1);
                stripper.setEndPage(i + 1);
                String content = stripper.getText(document);
                if (oriFlag && arrFlag && altFlag && infoFlag) {
                    break;
                }
                if (!oriFlag && (content.indexOf("标题1 ") != -1 || content.indexOf("title1 ") != -1)) {
                    oriFlag = true;
                    PDPageFitWidthDestination dest = new PDPageFitWidthDestination();
                    dest.setPage(page);
                    PDOutlineItem bookmark = new PDOutlineItem();
                    bookmark.setDestination(dest);
                    bookmark.setTitle("标题1");
                    //outline.appendChild(bookmark);  //老版本写法：比如版本1.8.7
                    outline.addLast(bookmark);;
                }
                i++;
            }
            document.save(outputFilePath);
            System.out.println("-------------- 写放目录完成------------------------");
        } catch (Exception e) {
            throw e;
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return outFilePath;
    }
	
}
