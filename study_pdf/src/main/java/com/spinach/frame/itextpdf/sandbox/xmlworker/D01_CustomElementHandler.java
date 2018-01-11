package com.spinach.frame.itextpdf.sandbox.xmlworker;

import com.itextpdf.text.Element;
import com.itextpdf.tool.xml.ElementHandler;
import com.itextpdf.tool.xml.Writable;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.pipeline.WritableElement;
import com.spinach.frame.itextpdf.sandbox.WrapToTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

//without @WrapToTest annotation, because this test only illustrated custom element handler
public class D01_CustomElementHandler {

    public static final String SRC = "src/main/webapp/resources/itextpdf/xml/walden.html";

    public static void main(String[] args) throws IOException {
        XMLWorkerHelper.getInstance().parseXHtml(new ElementHandler() {
            public void add(final Writable w) {
                if (w instanceof WritableElement) {
                    List<Element> elements = ((WritableElement) w).elements();
                    for (Element element : elements) {
                        System.out.println(element.getClass().getName());
                    }
                }

            }
        }, new FileInputStream(SRC), null);
    }



}
