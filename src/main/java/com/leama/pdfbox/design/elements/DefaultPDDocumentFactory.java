package com.leama.pdfbox.design.elements;

import org.apache.pdfbox.pdmodel.PDDocument;

public class DefaultPDDocumentFactory implements PDDocumentFactory {
    @Override
    public PDDocument create() {
        return new PDDocument();
    }
}
