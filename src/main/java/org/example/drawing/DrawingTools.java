package org.example.drawing;

import javafx.scene.paint.Color;

public class DrawingTools {
    private Color currentColor;
    private DrawMode drawMode;
    private double lineWidth;

    public Color getCurrentColor() {
        return this.currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public double getLineWidth() {
        return this.lineWidth;
    }

    public void setLineWidth(double width) {
        this.lineWidth = width;
    }

    public DrawMode getDrawMode() {
        return this.drawMode;
    }

    public void setDrawMode(DrawMode mode) {
        this.drawMode = mode;
    }

    public enum DrawMode {
        PEN, ERASER
    }

    public DrawingTools() {
        this.drawMode = DrawMode.PEN;
        this.currentColor = Color.BLACK;
        this.lineWidth = 2.0;
    }

    public boolean isPenMode() {
        return drawMode == DrawMode.PEN;
    }

    public boolean isEraserMode() {
        return drawMode == DrawMode.ERASER;
    }
}

