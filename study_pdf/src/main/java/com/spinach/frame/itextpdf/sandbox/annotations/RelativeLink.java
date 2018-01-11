/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27063677/use-of-relative-path-for-anchor-method-using-itext-for-pdf-generation
 */
package com.spinach.frame.itextpdf.sandbox.annotations;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class RelativeLink {
    public static final String DEST = "results/annotations/relative_link.pdf";
    public static final String XML = "src/main/webapp/resources/itextpdf/xml/data.xml";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RelativeLink().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        // first document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Chunk chunk = new Chunk("Click me");
        chunk.setAnchor("./" + new File(XML).getName());
        document.add(chunk);
        document.close();
    }
}
