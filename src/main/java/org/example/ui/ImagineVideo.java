package org.example.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.SpinnerValueFactory;
import javafx.geometry.Orientation;
import javafx.stage.Stage;

import org.example.drawing.DrawingTools;
import org.example.drawing.Mouse_Events;
import org.example.file.FileManager;
import org.example.Cam.Cam_Capture;

import java.io.IOException;

public class ImagineVideo extends Application {
    
    private Mouse_Events mouseEvents;
    private DrawingTools drawingTools;
    private Cam_Capture camCapture;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("AirDraw AI");
        BorderPane root = new BorderPane();

        // Criar o Canvas
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Configurar fundo branco
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);

        // Adicionar canvas ao root (centro)
        StackPane canvasContainer = new StackPane(canvas);

        // Criar ImageView para a câmera
        ImageView cameraView = new ImageView();
        cameraView.setFitWidth(400);
        cameraView.setFitHeight(300);
        cameraView.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");

        // HBox para colocar câmera e canvas lado a lado
        HBox mainContent = new HBox(10);
        mainContent.setStyle("-fx-padding: 10;");
        mainContent.getChildren().addAll(cameraView, canvasContainer);
        root.setCenter(mainContent);

        // Inicializar DrawingTools e Mouse_Events com o GraphicsContext
        drawingTools = new DrawingTools();
        mouseEvents = new Mouse_Events(gc, drawingTools);
        mouseEvents.Configurar(canvas);

        // Inicializar câmera
        camCapture = new Cam_Capture(0, cameraView);

        // Criar barra de ferramentas
        HBox toolbar = new HBox(8);
        toolbar.setStyle("-fx-padding:8; -fx-background-color:#f0f0f0;");

        Button penBtn = new Button("Caneta");
        Button eraserBtn = new Button("Borracha");

        ColorPicker colorPicker = new ColorPicker(drawingTools.getCurrentColor());
        colorPicker.setOnAction(e -> drawingTools.setCurrentColor(colorPicker.getValue()));

        Spinner<Double> thicknessSpinner = new Spinner<>();
        thicknessSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 80.0, drawingTools.getLineWidth(), 1.0));
        thicknessSpinner.valueProperty().addListener((obs, oldV, newV) -> drawingTools.setLineWidth(newV.doubleValue()));

        Button limparBtn = new Button("Limpar");
        limparBtn.setOnAction(e -> {
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        Button salvarBtn = new Button("Salvar PNG");
        salvarBtn.setOnAction(e -> FileManager.salvarImagemPNG(canvas, stage));

        Button abrirBtn = new Button("Abrir");
        abrirBtn.setOnAction(e -> FileManager.abrirImagemPNG(canvas, stage));

        // Botões da câmera
        Button ligarCameraBtn = new Button("Ligar Câmera");
        ligarCameraBtn.setOnAction(e -> camCapture.iniciarCaptura());

        Button desligarCameraBtn = new Button("Desligar Câmera");
        desligarCameraBtn.setOnAction(e -> camCapture.pararCaptura());

        Label modoLabel = new Label("Modo: Caneta");

        penBtn.setOnAction(e -> {
            drawingTools.setDrawMode(DrawingTools.DrawMode.PEN);
            modoLabel.setText("Modo: Caneta");
        });

        eraserBtn.setOnAction(e -> {
            drawingTools.setDrawMode(DrawingTools.DrawMode.ERASER);
            modoLabel.setText("Modo: Borracha");
        });

        toolbar.getChildren().addAll(
                penBtn, eraserBtn, 
                new Separator(Orientation.VERTICAL), 
                new Label("Cor:"), colorPicker,
                new Label("Espessura:"), thicknessSpinner, 
                new Separator(Orientation.VERTICAL), 
                limparBtn, salvarBtn, abrirBtn,
                new Separator(Orientation.VERTICAL), 
                ligarCameraBtn, desligarCameraBtn,
                new Separator(Orientation.VERTICAL), 
                modoLabel);

        root.setTop(toolbar);

        // Criar e mostrar a cena
        Scene scene = new Scene(root, 1400, 700);
        stage.setScene(scene);
        stage.show();
    }

}

