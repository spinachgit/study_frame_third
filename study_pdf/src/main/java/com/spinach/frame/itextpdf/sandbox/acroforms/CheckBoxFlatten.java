/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/34439850
 */
package com.spinach.frame.itextpdf.sandbox.acroforms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
@WrapToTest
public class CheckBoxFlatten {
    
    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/checkboxes.pdf";
    public static final String DEST = "results/acroforms/checkboxes_flat.pdf";
    
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CheckBoxFlatten().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.setFormFlattening(true);
        stamper.close();
    }
}
