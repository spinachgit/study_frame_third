/*
 * These examples are written by Bruno Lowagie in the context of an article about fonts.
 */
package com.spinach.frame.itextpdf.sandbox.fonts.tutorial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

/**
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class F06_Different_encodings {
    public static final String DEST = "results/fonts/tutorial/f06_unicode.pdf";
    public static final String FONT = "src/main/webapp/resources/itextpdf/fonts/FreeSans.ttf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F06_Different_encodings().createPdf(DEST);
    }
    
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        BaseFont bf1 = BaseFont.createFont(FONT, BaseFont.WINANSI, BaseFont.EMBEDDED);
        Font french = new Font(bf1, 12);
        BaseFont bf2 = BaseFont.createFont(FONT, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font czech = new Font(bf2, 12);
        BaseFont bf3 = BaseFont.createFont(FONT, "Cp1251", BaseFont.EMBEDDED);
        Font russian = new Font(bf3, 12);
        document.add(new Paragraph("Vous \u00eates d'o\u00f9?", french));
        document.add(new Paragraph("\u00c0 tout \u00e0 l'heure. \u00c0 bient\u00f4t.", french));
        document.add(new Paragraph("Je me pr\u00e9sente.", french));
        document.add(new Paragraph("C'est un \u00e9tudiant.", french));
        document.add(new Paragraph("\u00c7a va?", french));
        document.add(new Paragraph("Il est ing\u00e9nieur. Elle est m\u00e9decin.", french));
        document.add(new Paragraph("C'est une fen\u00eatre.", french));
        document.add(new Paragraph("R\u00e9p\u00e9tez, s'il vous pla\u00eet.", french));
        document.add(new Paragraph("Odkud jste?", czech));
        document.add(new Paragraph("Uvid\u00edme se za chvilku. M\u011bj se.", czech));
        document.add(new Paragraph("Dovolte, abych se p\u0159edstavil.", czech));
        document.add(new Paragraph("To je studentka.", czech));
        document.add(new Paragraph("V\u0161echno v po\u0159\u00e1dku?", czech));
        document.add(new Paragraph("On je in\u017een\u00fdr. Ona je l\u00e9ka\u0159.", czech));
        document.add(new Paragraph("Toto je okno.", czech));
        document.add(new Paragraph("Zopakujte to pros\u00edm.", czech));
        document.add(new Paragraph("\u041e\u0442\u043a\u0443\u0434\u0430 \u0442\u044b?", russian));
        document.add(new Paragraph("\u0423\u0432\u0438\u0434\u0438\u043c\u0441\u044f \u0432 \u043d\u0435\u043c\u043d\u043e\u0433\u043e. \u0423\u0432\u0438\u0434\u0438\u043c\u0441\u044f.", russian));
        document.add(new Paragraph("\u041f\u043e\u0437\u0432\u043e\u043b\u044c\u0442\u0435 \u043c\u043d\u0435 \u043f\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u044c\u0441\u044f.", russian));
        document.add(new Paragraph("\u042d\u0442\u043e \u0441\u0442\u0443\u0434\u0435\u043d\u0442.", russian));
        document.add(new Paragraph("\u0425\u043e\u0440\u043e\u0448\u043e?", russian));
        document.add(new Paragraph("\u041e\u043d \u0438\u043d\u0436\u0435\u043d\u0435\u0440. \u041e\u043d\u0430 \u0434\u043e\u043a\u0442\u043e\u0440.", russian));
        document.add(new Paragraph("\u042d\u0442\u043e \u043e\u043a\u043d\u043e.", russian));
        document.add(new Paragraph("\u041f\u043e\u0432\u0442\u043e\u0440\u0438\u0442\u0435, \u043f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430.", russian));
        document.close();
    }
}
