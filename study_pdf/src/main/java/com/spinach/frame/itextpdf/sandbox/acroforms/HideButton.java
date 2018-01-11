/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/38900816
 */
package com.spinach.frame.itextpdf.sandbox.acroforms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class HideButton {

    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/hello_button.pdf";
    public static final String DEST = "results/acroforms/hello_button_hidden.pdf";

    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new HideButton().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        form.setFieldProperty("Test", "setflags", PdfAnnotation.FLAGS_HIDDEN, null);
        stamper.close();
    }
}
