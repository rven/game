package com.example.knights;

import java.util.List;

public class ChessBoardModel {
    private final int size;
    private int playerX;
    private int playerY;
    private final Piece[][] board;

    public enum PieceType {
        QUEEN, ROOK, LIGHTHOUSE, KNIGHT, PLAYER
    }

    public static class Piece {
        PieceType type;
        boolean horizontalLighthouse; // Only used for the lighthouse

        public Piece(PieceType type) {
            this.type = type;
            if (type == PieceType.LIGHTHOUSE) {
                this.horizontalLighthouse = true; // Start with horizontal attack
            }
        }
    }

    public ChessBoardModel(int size, List<PieceConfiguration> pieceConfigurations) {
        this.size = size;
        board = new Piece[size][size];
        this.initializeBoard(pieceConfigurations);
    }

    public int getSize() {
        return size;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (isValidPosition(newX, newY)) {
            playerX = newX;
            playerY = newY;
        }
    }

    private boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        return board[x][y] == null || board[x][y].type != PieceType.LIGHTHOUSE;
    }

    public void switchLighthouseDirection() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null && board[i][j].type == PieceType.LIGHTHOUSE) {
                    board[i][j].horizontalLighthouse = !board[i][j].horizontalLighthouse;
                }
            }
        }
    }

    public void initializeBoard(List<PieceConfiguration> pieceConfigurations) {
        // Iterate through the list of piece configurations and place pieces on the board
        for (PieceConfiguration config : pieceConfigurations) {
            int x = config.getX();
            int y = config.getY();
            PieceType type = config.getPieceType();

            // Ensure the coordinates are within the bounds of the board
            if (x >= 0 && x < size && y >= 0 && y < size) {
                board[x][y] = new Piece(type);

                // If the piece is the player, update playerX and playerY
                if (type == PieceType.PLAYER) {
                    playerX = x;
                    playerY = y;
                }
            } else {
                // Handle the error case for out-of-bounds coordinates
                System.out.println("Invalid coordinates: (" + x + ", " + y + ")");
            }
        }
    }

    // Helper class to hold piece configuration
    public static class PieceConfiguration {
        private final int x;
        private final int y;
        private final PieceType pieceType;

        public PieceConfiguration(int x, int y, PieceType pieceType) {
            this.x = x;
            this.y = y;
            this.pieceType = pieceType;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public PieceType getPieceType() {
            return pieceType;
        }
    }
}