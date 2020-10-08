package com.leama.pdfbox.design.text.annotations;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.leama.pdfbox.design.elements.render.RenderContext;
import com.leama.pdfbox.design.elements.render.RenderListener;
import com.leama.pdfbox.design.text.Alignment;
import com.leama.pdfbox.design.text.DrawContext;
import com.leama.pdfbox.design.text.DrawListener;
import com.leama.pdfbox.design.text.DrawableText;
import com.leama.pdfbox.design.text.Position;

/**
 * This listener has to be passed to all
 * {@link DrawableText#drawText(org.apache.pdfbox.pdmodel.edit.PDPageContentStream, Position, Alignment, DrawListener)
 * draw()} methods, in order collect all annotation metadata. After all drawing
 * is done, you have to call {@link #finalizeAnnotations()} which creates all
 * necessary annotations and sets them to the corresponding pages. This listener
 * is used by the the rendering API, but you may also use it with the low-level
 * text API.
 */
public class AnnotationDrawListener implements DrawListener, RenderListener {

    private final DrawContext drawContext;
    private final Iterable<AnnotationProcessor> annotationProcessors;

    /**
     * Creates an AnnotationDrawListener with the given {@link DrawContext}.
     *
     * @param drawContext the context which provides the {@link PDDocument} and the
     *                    {@link PDPage} currently drawn to.
     */
    public AnnotationDrawListener(final DrawContext drawContext) {
        this.drawContext = drawContext;
        annotationProcessors = AnnotationProcessorFactory
                .createAnnotationProcessors();
    }

    @Override
    public void drawn(Object drawnObject, Position upperLeft, float width,
                      float height) {
        if (!(drawnObject instanceof Annotated)) {
            return;
        }
        for (AnnotationProcessor annotationProcessor : annotationProcessors) {
            try {
                annotationProcessor.annotatedObjectDrawn(
                        (Annotated) drawnObject, drawContext, upperLeft, width,
                        height);
            } catch (IOException e) {
                throw new RuntimeException(
                        "exception on annotation processing", e);
            }
        }
    }

    /**
     * @throws IOException by pdfbox.
     * @deprecated user {@link #afterRender()} instead.
     */
    @Deprecated
    public void finalizeAnnotations() throws IOException {
        afterRender();
    }

    @Override
    public void beforePage(RenderContext renderContext) throws IOException {
        for (AnnotationProcessor annotationProcessor : annotationProcessors) {
            try {
                annotationProcessor.beforePage(drawContext);
            } catch (IOException e) {
                throw new RuntimeException(
                        "exception on annotation processing", e);
            }
        }
    }

    @Override
    public void afterPage(RenderContext renderContext) throws IOException {
        for (AnnotationProcessor annotationProcessor : annotationProcessors) {
            try {
                annotationProcessor.afterPage(drawContext);
            } catch (IOException e) {
                throw new RuntimeException(
                        "exception on annotation processing", e);
            }
        }
    }


    public void afterRender() throws IOException {
        for (AnnotationProcessor annotationProcessor : annotationProcessors) {
            try {
                annotationProcessor.afterRender(drawContext.getPdDocument());
            } catch (IOException e) {
                throw new RuntimeException(
                        "exception on annotation processing", e);
            }
        }
    }

}
