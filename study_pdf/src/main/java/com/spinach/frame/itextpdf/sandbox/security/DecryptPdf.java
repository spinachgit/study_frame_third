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
 * 加密文件的处理 PdfReader(src,"passworld".getBytes());
 * @author:whh
 * @date:2018年1月11日下午4:47:49
 */
public class DecryptPdf {

    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/hello_encrypted.pdf";
    public static final String DEST = "results/security/hello.pdf";
    
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new DecryptPdf().manipulatePdf(SRC, DEST);
    }
    
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src, "World".getBytes());
        System.out.println(new String(reader.computeUserPassword()));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }
}
