/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/28601068/get-names-field-from-interactive-form-pdf
 */
package com.spinach.frame.itextpdf.sandbox.xfa;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.XfaForm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class FillXFA2 {

    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/xfa_form_poland.pdf";
    public static final String XML = "src/main/webapp/resources/itextpdf/xml/xfa_form_poland.xml";
    public static final String DEST = "results/xfa/xfa_form_poland_filled.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillXFA2().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
        PdfReader.unethicalreading = true;
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(dest));
        AcroFields form = stamper.getAcroFields();
        XfaForm xfa = form.getXfa();
        xfa.fillXfaForm(new FileInputStream(XML));
        stamper.close();
        reader.close();
    }
}
