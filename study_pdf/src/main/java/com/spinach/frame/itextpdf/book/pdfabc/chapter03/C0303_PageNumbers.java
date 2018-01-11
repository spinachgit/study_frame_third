/**
 * This is an example was written by Bruno Lowagie.
 * It is used in the book "The ABC of PDF".
 * This book can be downloaded here: https://leanpub.com/itext_pdfabc/
 */
package com.spinach.frame.itextpdf.book.pdfabc.chapter03;

import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;

import java.io.IOException;

public class C0303_PageNumbers {
    public static final String SRC = "src/main/webapp/resources/itextpdf/pdfs/primes.pdf";
	
    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader(SRC);
        int n = reader.getNumberOfPages();
        PdfDictionary page;
        for (int i = 1; i <= n; i++) {
            page = reader.getPageN(i);
            System.out.println("The parent of page " + i + " is " + page.get(PdfName.PARENT));
        }
        reader.close();
    }
}
