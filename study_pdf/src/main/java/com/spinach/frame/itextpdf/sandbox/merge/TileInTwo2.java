/*
 * Example written by Bruno Lowagie in answer to a question on StackOverflow:
 * http://stackoverflow.com/questions/27600809/divide-pdf-exact-equal-half-using-itextsharp
 */
package com.spinach.frame.itextpdf.sandbox.merge;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class TileInTwo2 {
    
    /** The original PDF file. */
    public static final String SRC
        = "src/main/webapp/resources/itextpdf/pdfs/united_states.pdf";

    /** The resulting PDF file. */
    public static final String DEST
        = "results/merge/unitedstates_tiled2.pdf";
    
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TileInTwo2().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
    	// Creating a reader
        PdfReader reader = new PdfReader(src);
        int n = reader.getNumberOfPages();
        // step 1
        Rectangle mediabox = new Rectangle(getHalfPageSize(reader.getPageSizeWithRotation(1)));
        Document document = new Document(mediabox);
        // step 2
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        PdfContentByte content = writer.getDirectContent();
        PdfImportedPage page;
        int i = 1;
        while (true) {
            page = writer.getImportedPage(reader, i);
            content.addTemplate(page, 0, 0);
            document.newPage();
            content.addTemplate(page, -mediabox.getWidth(), 0);
            if (++i > n)
                break;
            mediabox = new Rectangle(getHalfPageSize(reader.getPageSizeWithRotation(i)));
            document.setPageSize(mediabox);
            document.newPage();
        }
        // step 5
        document.close();
        reader.close();
    }
    
    public Rectangle getHalfPageSize(Rectangle pagesize) {
        float width = pagesize.getWidth();
        float height = pagesize.getHeight();
        return new Rectangle(width / 2, height);
    }
}
