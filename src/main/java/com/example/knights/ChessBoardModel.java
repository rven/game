package com.example.knights;

public class ChessBoardModel {
    private static final int SIZE = 8;
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

    public ChessBoardModel() {
        board = new Piece[SIZE][SIZE];
        initializeLevel1();
    }

    public int getSize() {
        return SIZE;
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
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public void switchLighthouseDirection() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != null && board[i][j].type == PieceType.LIGHTHOUSE) {
                    board[i][j].horizontalLighthouse = !board[i][j].horizontalLighthouse;
                }
            }
        }
    }

    private void initializeLevel1() {
        // Initialize the level 1 configuration
        playerX = 0;
        playerY = 0;
        board[0][0] = new Piece(PieceType.PLAYER);
        board[3][3] = new Piece(PieceType.QUEEN);
        board[5][5] = new Piece(PieceType.ROOK);
        board[7][7] = new Piece(PieceType.LIGHTHOUSE);
        board[1][2] = new Piece(PieceType.KNIGHT);
    }
}
