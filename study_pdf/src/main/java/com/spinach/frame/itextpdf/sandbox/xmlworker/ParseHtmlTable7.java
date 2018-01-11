/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/28332971/xmlworker-cells-vertical-align-not-working
 */
package com.spinach.frame.itextpdf.sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

/**
 *
 * @author iText
 */
@WrapToTest
public class ParseHtmlTable7 {
    public static final String DEST = "results/xmlworker/html_table_7.pdf";
    public static final String HTML = "src/main/webapp/resources/itextpdf/xml/table4_css.html";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHtmlTable7().createPdf(DEST);
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
                new FileInputStream(HTML));
        // step 5
        document.close();
    }
}
