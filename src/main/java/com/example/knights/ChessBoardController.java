package com.example.knights;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChessBoardController {
    private final ChessBoardModel model;
    private final ChessBoardView view;

    public ChessBoardController(ChessBoardModel model, ChessBoardView view) {
        this.model = model;
        this.view = view;
        updateView();
        startLighthouseTimer();
    }

    public void start(Stage primaryStage) {
        Scene scene = new Scene(view.getBoard(), model.getSize() * 80, model.getSize() * 80);
        scene.setOnKeyPressed(this::handleKeyPress);

        primaryStage.setTitle("Knights & Queens");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.getBoard().requestFocus(); // Zorg ervoor dat het AnchorPane de focus heeft voor het detecteren van toetsaanslagen
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                model.movePlayer(0, -1);
                break;
            case DOWN:
                model.movePlayer(0, 1);
                break;
            case LEFT:
                model.movePlayer(-1, 0);
                break;
            case RIGHT:
                model.movePlayer(1, 0);
                break;
            default:
                break;
        }
        updateView();
    }

    private void updateView() {
        view.updatePlayerPosition(model.getPlayerX(), model.getPlayerY());
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                ChessBoardModel.Piece piece = model.getPiece(i, j);
                if (piece != null) {
                    view.updatePiecePosition(i, j, piece);
                } else {
                    view.clearPiecePosition(i, j); // Zorg ervoor dat de positie wordt gewist als er geen stuk is
                }
            }
        }
    }

    private void startLighthouseTimer() {
        Timeline lighthouseTimer = new Timeline(new KeyFrame(Duration.seconds(10), _ -> {
            model.switchLighthouseDirection();
            updateView();
        }));
        lighthouseTimer.setCycleCount(Timeline.INDEFINITE);
        lighthouseTimer.play();
    }
}
