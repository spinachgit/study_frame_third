/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/29290668/set-line-spacing-when-using-xmlworker-to-parse-html-to-pdf-itextsharp-c-sharp
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
public class ParseHeaders {
    public static final String DEST = "results/xmlworker/headers.pdf";
    public static final String HTML = "src/main/webapp/resources/itextpdf/xml/headers.html";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ParseHeaders().createPdf(DEST);
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
        writer.setTagged();
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML));
        // step 5
        document.close();
    }
}
