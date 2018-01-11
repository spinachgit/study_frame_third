/*
 * This example was written by Bruno Lowagie
 */
package com.spinach.frame.itextpdf.zugferd;

import java.sql.SQLException;
import java.util.List;

import com.spinach.frame.itextpdf.zugferd.pojo.Invoice;
import com.spinach.frame.itextpdf.zugferd.pojo.PojoFactory;

/**
 * A simple example to test the database
 */
public class DatabaseTest {
    public static void main(String[] args) throws SQLException {
        PojoFactory factory = PojoFactory.getInstance();
        List<Invoice> invoices = factory.getInvoices();
        for (Invoice invoice : invoices)
            System.out.println(invoice.toString());
        factory.close();
    }
}
