/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/37652181
 */
package com.spinach.frame.itextpdf.sandbox.annotations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;import com.spinach.frame.itextpdf.sandbox.WrapToTest;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest
public class AddAccepted {

    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/hello_sticky_note.pdf";
    public static final String DEST = "results/annotations/hello_accepted.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddAccepted().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary page = reader.getPageN(1);
        PdfArray annots = page.getAsArray(PdfName.ANNOTS);
        PdfDictionary sticky = annots.getAsDict(0);
        PdfArray stickyRect = sticky.getAsArray(PdfName.RECT);
        PdfDictionary popup = annots.getAsDict(1);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfWriter writer = stamper.getWriter();
        Rectangle stickyRectangle = new Rectangle(
            stickyRect.getAsNumber(0).floatValue(), stickyRect.getAsNumber(1).floatValue(),
            stickyRect.getAsNumber(2).floatValue(), stickyRect.getAsNumber(3).floatValue()
        );
        PdfAnnotation replySticky = PdfAnnotation.createText(
                writer, stickyRectangle, "Bruno", "Accepted by Bruno", false, "Comment");
        replySticky.put(PdfName.IRT, annots.getAsIndirectObject(0));
        replySticky.put(PdfName.STATE, new PdfString("Accepted"));
        PdfNumber n = sticky.getAsNumber(PdfName.F);
        replySticky.put(PdfName.F, new PdfNumber(n.intValue() | PdfAnnotation.FLAGS_HIDDEN));
        replySticky.put(new PdfName("StateModel"), new PdfString("Review"));
        stamper.addAnnotation(replySticky, 1);
        stamper.close();
    }
}
