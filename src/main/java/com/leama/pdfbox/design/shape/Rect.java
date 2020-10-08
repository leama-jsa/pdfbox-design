package com.leama.pdfbox.design.shape;

import com.leama.pdfbox.design.text.Position;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

/**
 * A simple rectangular shape.
 */
public class Rect extends AbstractShape {

    @Override
    public void add(PDDocument pdDocument, PDPageContentStream contentStream,
                    Position upperLeft, float width, float height) throws IOException {
        contentStream.addRect(upperLeft.getX(), upperLeft.getY() - height,
                width, height);
    }

}
