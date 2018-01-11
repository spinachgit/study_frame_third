/**
 * Example written for JavaOne 2016.
 * Differences between iText 5 and iText 7 are discussed in the JavaOne talk
 * "Oops, I broke my API" by Raf Hens and Bruno Lowagie.
 * This is the iText 5 version of one of the examples.
 */
package com.spinach.frame.itextpdf.javaone.edition16;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class HelloWorldStyles {

    public static final String DEST
        = "results/javaone/edition16/hello_styles.pdf";
    
    public static void main(String[] args)
    	throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
    	new HelloWorldStyles().createPdf(DEST);
    }

    public void createPdf(String dest)
	throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font code = new Font(FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
        Paragraph p = new Paragraph("In this example, named ");
        p.add(createBgChunk("HelloWorldStyles", code));
        p.add(", we experiment with some text in ");
        p.add(createBgChunk("code style", code));
        p.add(".");
        document.add(p);
        document.close();
    }
    
    public Chunk createBgChunk(String s, Font font) {
        Chunk chunk = new Chunk(s, font);
        chunk.setBackground(BaseColor.LIGHT_GRAY);
        return chunk;
    }
}
