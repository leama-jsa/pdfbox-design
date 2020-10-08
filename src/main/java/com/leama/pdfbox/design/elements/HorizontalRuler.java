package com.leama.pdfbox.design.elements;

import com.leama.pdfbox.design.shape.Stroke;
import com.leama.pdfbox.design.text.DrawListener;
import com.leama.pdfbox.design.text.Position;
import com.leama.pdfbox.design.text.WidthRespecting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.*;
import java.io.IOException;

/**
 * A horizontal ruler that adjust its width to the given
 * {@link WidthRespecting#getMaxWidth() max width}.
 */
public class HorizontalRuler implements Drawable, Element, WidthRespecting {

    private Stroke stroke;
    private Color color;
    private float maxWidth = -1f;

    public HorizontalRuler(Stroke stroke, Color color) {
        super();
        this.stroke = stroke;
        this.color = color;
    }

    /**
     * @return the stroke to draw the ruler with.
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * @return the color to draw the ruler with.
     */
    public Color getColor() {
        return color;
    }

    @Override
    public float getMaxWidth() {
        return maxWidth;
    }

    @Override
    public void setMaxWidth(float maxWidth) {
        this.maxWidth = maxWidth;
    }

    @Override
    public float getWidth() throws IOException {
        return getMaxWidth();
    }

    @Override
    public float getHeight() throws IOException {
        if (getStroke() == null) {
            return 0f;
        }
        return getStroke().getLineWidth();
    }

    @Override
    public Position getAbsolutePosition() {
        return null;
    }

    @Override
    public void draw(PDDocument pdDocument, PDPageContentStream contentStream,
                     Position upperLeft, DrawListener drawListener) throws IOException {
        if (getColor() != null) {
            contentStream.setStrokingColor(getColor());
        }
        if (getStroke() != null) {
            getStroke().applyTo(contentStream);
            float x = upperLeft.getX();
            float y = upperLeft.getY() - getStroke().getLineWidth() / 2;
            contentStream.addLine(x, y, x + getWidth(), y);
            contentStream.stroke();
        }
        if (drawListener != null) {
            drawListener.drawn(this, upperLeft, getWidth(), getHeight());
        }
    }

    @Override
    public Drawable removeLeadingEmptyVerticalSpace() {
        return this;
    }

}
