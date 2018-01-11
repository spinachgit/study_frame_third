/*
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27867868/how-can-i-decrypt-a-pdf-document-with-the-owner-password
 */
package com.spinach.frame.itextpdf.sandbox.security;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 引例演示：只读PDF的操作。
 * 此例：打开只读PDF变成另一个只读文档。
 * @author:whh
 * @date:2018年1月11日下午6:14:42
 */
public class DecryptPdf2 {

    //public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/hello_encrypted2.pdf";
    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/lock.pdf";
    public static final String DEST = "results/security/hello2.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DecryptPdf2().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader.unethicalreading = true;
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}
