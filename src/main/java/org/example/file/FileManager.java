package org.example.file;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileManager {

    public static void salvarImagemPNG(Canvas canvas, Window owner) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Imagem");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Images", "*.png"));
        File file = fileChooser.showSaveDialog(owner);
        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(bufferedImage, "png", file);
                System.out.println("Imagem salva em: " + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void abrirImagemPNG(Canvas canvas, Window owner) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Imagem");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Images", "*.png"));
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            try {
                BufferedImage buffered = ImageIO.read(file);
                WritableImage fxImage = SwingFXUtils.toFXImage(buffered, null);
                canvas.getGraphicsContext2D().drawImage(fxImage, 0, 0);
                System.out.println("Imagem aberta: " + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

