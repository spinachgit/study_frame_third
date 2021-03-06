/*
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/27015644/add-a-rich-textbox-to-pdf-using-itextsharp
 */
package com.spinach.frame.itextpdf.sandbox.xmlworker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class HtmlContentForCell {
    
    public static final String HTML = "<p>Overview&#160;line1</p>"
            + "<p>Overview&#160;line2</p><p>Overview&#160;line3</p>"
            + "<p>Overview&#160;line4</p><p>Overview&#160;line4</p>"
            + "<p>Overview&#160;line5&#160;</p>";
    public static final String CSS = "p { font-family: Cardo; }";
    public static final String DEST = "results/xmlworker/html_in_cell.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HtmlContentForCell().createPdf(DEST);
    }

    public void createPdf(String file) throws IOException, DocumentException {
        FontFactory.register("src/main/webapp/resources/itextpdf/fonts/Cardo-Regular.ttf");

        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        PdfPTable table = new PdfPTable(2);
        table.addCell("Some rich text:");
        PdfPCell cell = new PdfPCell();
        for (Element e : XMLWorkerHelper.parseToElementList(HTML, CSS)) {
            cell.addElement(e);
        }
        table.addCell(cell);
        document.add(table);
        // step 5
        document.close();
    }

}
