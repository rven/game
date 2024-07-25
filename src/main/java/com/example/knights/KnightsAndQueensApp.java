package com.example.knights;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class KnightsAndQueensApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Bord 1
        List<ChessBoardModel.PieceConfiguration> pieceConfigurations1 = new ArrayList<>();
        pieceConfigurations1.add(new ChessBoardModel.PieceConfiguration(7, 7, ChessBoardModel.PieceType.PLAYER));
        pieceConfigurations1.add(new ChessBoardModel.PieceConfiguration(3, 3, ChessBoardModel.PieceType.QUEEN));
        pieceConfigurations1.add(new ChessBoardModel.PieceConfiguration(5, 5, ChessBoardModel.PieceType.QUEEN));
        pieceConfigurations1.add(new ChessBoardModel.PieceConfiguration(4, 2, ChessBoardModel.PieceType.LIGHTHOUSE));
        ChessBoardModel model1 = new ChessBoardModel(8, pieceConfigurations1); // 8x8 bord
        ChessBoardView view1 = new ChessBoardView(8);
        ChessBoardController controller1 = new ChessBoardController(model1, view1);

        Stage stage1 = new Stage();
        controller1.start(stage1);
        stage1.setTitle("Knights & Queens - Board 1");

        // Bord 2
        List<ChessBoardModel.PieceConfiguration> pieceConfigurations2 = new ArrayList<>();
        pieceConfigurations2.add(new ChessBoardModel.PieceConfiguration(0, 9, ChessBoardModel.PieceType.PLAYER));
        pieceConfigurations2.add(new ChessBoardModel.PieceConfiguration(5, 5, ChessBoardModel.PieceType.ROOK));
        pieceConfigurations2.add(new ChessBoardModel.PieceConfiguration(7, 7, ChessBoardModel.PieceType.LIGHTHOUSE));
        pieceConfigurations2.add(new ChessBoardModel.PieceConfiguration(1, 2, ChessBoardModel.PieceType.KNIGHT));
        ChessBoardModel model2 = new ChessBoardModel(10, pieceConfigurations2); // 10x10 bord
        ChessBoardView view2 = new ChessBoardView(10);
        ChessBoardController controller2 = new ChessBoardController(model2, view2);

        Stage stage2 = new Stage();
        controller2.start(stage2);
        stage2.setTitle("Knights & Queens - Board 2");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
