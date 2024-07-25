package com.example.knights;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class ChessBoardView {
    private static final int TILE_SIZE = 80;
    private final AnchorPane board;
    private final ImageView player;

    private final Image queenImage;
    private final Image rookImage;
    private final Image lighthouseImage;
    private final Image knightImage;

    private final ImageView[][] pieceViews; // Add this to keep track of piece views

    public ChessBoardView(int size) {
        board = new AnchorPane();
        pieceViews = new ImageView[size][size]; // Initialize the array

        // Load images
        queenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/queen.png")));
        rookImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/rook.png")));
        lighthouseImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/lighthouse.png")));
        knightImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/knight.png")));
        Image playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player.png")));

        // Create the board
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                tile.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                tile.setLayoutX(col * TILE_SIZE);
                tile.setLayoutY(row * TILE_SIZE);
                board.getChildren().add(tile);
            }
        }

        player = new ImageView(playerImage);
        player.setFitWidth(TILE_SIZE);
        player.setFitHeight(TILE_SIZE);
        board.getChildren().add(player);
    }

    public AnchorPane getBoard() {
        return board;
    }

    public void updatePiecePosition(int x, int y, ChessBoardModel.Piece piece) {
        clearPiecePosition(x, y);

        ImageView pieceView;
        switch (piece.type) {
            case QUEEN:
                pieceView = new ImageView(queenImage);
                break;
            case ROOK:
                pieceView = new ImageView(rookImage);
                break;
            case LIGHTHOUSE:
                pieceView = new ImageView(lighthouseImage);
                if (!piece.horizontalLighthouse) {
                    pieceView.setRotate(90);
                }
                break;
            case KNIGHT:
                pieceView = new ImageView(knightImage);
                break;
            case PLAYER:
                pieceView = player;
                break;
            default:
                return;
        }

        pieceView.setFitWidth(TILE_SIZE);
        pieceView.setFitHeight(TILE_SIZE);
        if (piece.type != ChessBoardModel.PieceType.PLAYER) {
            pieceView.setLayoutX(x * TILE_SIZE);
            pieceView.setLayoutY(y * TILE_SIZE);
            board.getChildren().add(pieceView);
            pieceViews[x][y] = pieceView;
        }
    }

    public void updatePlayerPosition(int x, int y) {
        player.setLayoutX(x * TILE_SIZE);
        player.setLayoutY(y * TILE_SIZE);
    }

    public void clearPiecePosition(int x, int y) {
        if (pieceViews[x][y] != null) {
            board.getChildren().remove(pieceViews[x][y]);
            pieceViews[x][y] = null;
        }
    }
}
