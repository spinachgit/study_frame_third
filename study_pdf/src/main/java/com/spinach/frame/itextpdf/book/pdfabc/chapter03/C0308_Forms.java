/**
 * This is an example was written by Bruno Lowagie.
 * It is used in the book "The ABC of PDF".
 * This book can be downloaded here: https://leanpub.com/itext_pdfabc/
 */
package com.spinach.frame.itextpdf.book.pdfabc.chapter03;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.XfaForm;

import java.io.File;
import java.io.IOException;

public class C0308_Forms {
    public static final String SRC1 = "src/main/webapp/resources/itextpdf/pdfs/pages.pdf";
    public static final String SRC2 = "src/main/webapp/resources/itextpdf/pdfs/datasheet.pdf";
    public static final String SRC3 = "src/main/webapp/resources/itextpdf/pdfs/xfa_movies.pdf";
    public static final String SRC4 = "src/main/webapp/resources/itextpdf/pdfs/xfa_movie.pdf";
    
    public static void main(String[] args) throws IOException {
        inspectForm(new File(SRC1));
        inspectForm(new File(SRC2));
        inspectForm(new File(SRC3));
        inspectForm(new File(SRC4));
    }
    
    public static void inspectForm(File file) throws IOException {
        System.out.print(file.getName());
        System.out.print(": ");
        PdfReader reader = new PdfReader(file.getAbsolutePath());
        AcroFields form = reader.getAcroFields();
        XfaForm xfa = form.getXfa();
        System.out.println(
            xfa.isXfaPresent() ?
                  form.getFields().size() == 0 ? "XFA form" : "Hybrid form"
                : form.getFields().size() == 0 ? "not a form" : "AcroForm");
        reader.close();
    }
}
