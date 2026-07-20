package org.example.drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Mouse_Events {
    private GraphicsContext gc;
    private DrawingTools drawingTools;

    public Mouse_Events(GraphicsContext gc, DrawingTools drawingTools) {
        this.gc = gc;
        this.drawingTools = drawingTools;
    }

    public void Configurar(Canvas canvas) {
        canvas.setOnMousePressed(e -> {
            // aplicar configuração atual antes de começar o traço
            if (drawingTools.isPenMode()) {
                gc.setStroke(drawingTools.getCurrentColor());
                gc.setLineWidth(drawingTools.getLineWidth());
            }
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
        });

        canvas.setOnMouseDragged(e -> {
            aplicarFerramentas(e.getX(), e.getY());
        });

        canvas.setOnMouseReleased(e -> {
            gc.closePath();
        });
    }

    private void aplicarFerramentas(double x, double y) {
        if (drawingTools.isPenMode()) {
            gc.setStroke(drawingTools.getCurrentColor());
            gc.setLineWidth(drawingTools.getLineWidth());
            gc.lineTo(x, y);
            gc.stroke();
        } else { // ERASER
            double size = drawingTools.getLineWidth();
            gc.clearRect(x - size / 2, y - size / 2, size, size);
        }
    }
}

