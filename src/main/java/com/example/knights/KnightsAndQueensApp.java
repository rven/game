package com.example.knights;

import javafx.application.Application;
import javafx.stage.Stage;

public class KnightsAndQueensApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        ChessBoardModel model = new ChessBoardModel();
        ChessBoardView view = new ChessBoardView();
        ChessBoardController controller = new ChessBoardController(model, view);
        controller.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
