package org.example.Cam;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

import java.io.ByteArrayInputStream;
import java.io.File;

public class Cam_Capture {

    private VideoCapture capture;
    private Mat frame;
    private Thread cameraThread;
    private volatile boolean capturando;
    private final int cameraIndex;
    private final ImageView imageView;

    public Cam_Capture(int cameraIndex, ImageView imageView) {
        this.cameraIndex = cameraIndex;
        this.imageView = imageView;

        // Mat será inicializado quando necessário
        frame = new Mat();
    }

    public boolean estaCapturando() {
        return capturando && capture != null && capture.isOpened();
    }

    public Mat getFrameAtual() {
        return new Mat(frame);
    }

    public void iniciarCaptura() {
        if (capturando) return;

        capture = new VideoCapture(cameraIndex);
        if (!capture.isOpened()) {
            System.err.println("Erro: câmera não pode ser aberta");
            return;
        }

        capturando = true;
        cameraThread = new Thread(this::capturaFrames);
        cameraThread.setDaemon(true);
        cameraThread.start();
    }

    private void capturaFrames() {
        while (capturando && capture.isOpened()) {
            try {
                if (capture.read(frame)) {
                    Image image = matToImage(frame);
                    if (imageView != null && image != null) {
                        Platform.runLater(() -> imageView.setImage(image));
                    }
                }

                Thread.sleep(33);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                System.err.println("Thread de captura interrompida");
            } catch (Exception e) {
                System.err.println("Erro ao capturar frames: " + e.getMessage());
            }
        }
    }

    private Image matToImage(Mat mat) {
        try {
            BytePointer buf = new BytePointer();
            opencv_imgcodecs.imencode(".jpg", mat, buf);
            byte[] bytes = new byte[(int) buf.limit()];
            buf.get(bytes);
            buf.deallocate();
            return new Image(new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            System.err.println("Erro ao converter imagem: " + e.getMessage());
            return null;
        }
    }

    public void pararCaptura() {
        capturando = false;

        if (capture != null) {
            try {
                capture.release();
            } catch (Exception ignored) {
            }
        }

        if (cameraThread != null && cameraThread.isAlive()) {
            try {
                cameraThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Erro ao parar thread: " + e.getMessage());
            }
        }

        System.out.println("Câmera desligada!");
    }
    public String Salve_Frame(){
        if(frame == null|| frame.size().empty()){
            System.out.println("Erro ao salvar Frame , nenhum frame capturado");
            return null;
        }


        File pasta = new File("C:\\Temp");
        if (!pasta.exists()){
            pasta.mkdir();
        }
        String caminho = "C:\\Temp\\frame_atual.jpg";

        Boolean salvo = opencv_imgcodecs.imwrite(caminho,frame);
        if (salvo) {
            System.out.println("Frame salvo em: " + caminho);
            return caminho;
        } else {
            System.err.println("Erro ao salvar o frame.");
            return null;
        }


    }


}

