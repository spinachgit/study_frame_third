package com.spinach.frame.itextpdf.sandbox.acroforms.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

@WrapToTest(compareRenders = true)
public class FlattenForm {

    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/state.pdf";
    public static final String DEST = "results/acroforms/reporting/california2.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FlattenForm().manipulatePdf(SRC, DEST);
    }
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(dest));
        AcroFields fields = stamper.getAcroFields();
        fields.setField("name", "CALIFORNIA");
        fields.setField("abbr", "CA");
        fields.setField("capital", "Sacramento");
        fields.setField("city", "Los Angeles");
        fields.setField("population", "36,961,664");
        fields.setField("surface", "163,707");
        fields.setField("timezone1", "PT (UTC-8)");
        fields.setField("timezone2", "-");
        fields.setField("dst", "YES");
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
    }
}