/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/31020576/how-to-customize-fontstyles-while-creating-pdf-from-html-using-itext
 */
package com.spinach.frame.itextpdf.sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

//without @WrapToTest annotation, because there are no Windows fonts on the linux server (Calibri)
public class ParseHtmlTable11 {
    public static final String DEST = "results/xmlworker/html_table_11.pdf";
    public static final String HTML = "src/main/webapp/resources/itextpdf/xml/table11.html";

    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtmlTable11().createPdf(DEST);
    }
        
    /**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML),Charset.forName("cp1252"), new XMLWorkerFontProvider("src/main/webapp/resources/itextpdf/fonts/"));
        // step 5
        document.close();
    }
}
