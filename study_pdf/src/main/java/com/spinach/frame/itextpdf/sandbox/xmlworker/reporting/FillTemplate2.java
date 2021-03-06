package com.spinach.frame.itextpdf.sandbox.xmlworker.reporting;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.html.Tags;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WrapToTest
public class FillTemplate2 {

    public static final String DEST = "results/xmlworker/report2.pdf";
    public static final String PDF = "src/main/webapp/resources/itextpdf/pdfs/stationery.pdf";
    public static final String HTML = "src/main/webapp/resources/itextpdf/xml/movies.html";
    public static final String CSS = "src/main/webapp/resources/itextpdf/xml/style2.css";
    
    public void createPdf(String result) throws IOException, DocumentException {
        FillTemplateHelper template = new FillTemplateHelper(PDF);
        template.setSender("Bruno Lowagie\nAdolf Baeyensstraat 121\n9040 Sint-Amandsberg\nBELGIUM");
        template.setReceiver("iText Software Corp.\nCambridge Innovation Center\n1 Broadway, 14th Floor\nCambridge, MA 02142 USA");
        // step 1
        Document document = new Document(template.getPageSize());
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
        writer.setPageEvent(template);
        // step 3
        document.open();
        // step 4
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(template.getBody());
	ElementList elements = FillTemplateHelper.parseHtml(HTML, CSS, Tags.getHtmlTagProcessorFactory());
        int status;
        float yLine;
        int rowsDrawn;
        for (Element e : elements) {
            if (!ColumnText.isAllowedElement(e))
                continue;
            if (e instanceof PdfPTable)
                ((PdfPTable)e).setHeaderRows(1);
            yLine = ct.getYLine();
            ct.addElement(e);
            status = ct.go(true);
            // the content fits:
            if (!ColumnText.hasMoreText(status)) {
                 // return to the original position
                ct.setYLine(yLine);
                // add the element FOR REAL
                ct.addElement(e);
                ct.go();
            }
            else {
                rowsDrawn = ct.getRowsDrawn();
                ct.setText(null);
                ct.addElement(e);
                ct.setSimpleColumn(template.getBody());
                status = ct.go(true);
                if (ColumnText.hasMoreText(status) && rowsDrawn > 2) {
                    ct.setYLine(yLine);
                    ct.setText(null);
                    ct.addElement(e);
                    ct.go();
                }
                else {
                    ct.setText(null);
                    ct.addElement(e);
                }
                document.newPage();
                ct.setSimpleColumn(template.getBody());
                status = ct.go();
                while (ColumnText.hasMoreText(status)) {
                    document.newPage();
                    ct.setSimpleColumn(template.getBody());
                    status = ct.go();
                }
            }
        }
        // step 5
        document.close();
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillTemplate2().createPdf(DEST);
    }
}
