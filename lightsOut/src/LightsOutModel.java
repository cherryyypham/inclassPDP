import java.util.Random;

public class LightsOutModel {
    private int gridSize;
    private boolean[][] board;
    private int moveCount;
}

public LightsOutModel(int size) {
    this.gridSize = size;
    this.board = new boolean[size][size];
    this.moveCount = 0;
}

public void randomizeBoard() {
    Random rand = new Random();
    int moves = gridSize * 2;
    for (int i = 0; i < moves; i++) {
        int row = rand.nextInt(gridSize);
        int col = rand.nextInt(gridSize);
        applyMove(row, col, false);
    }

    // Reset move counter
    moveCount = 0;
}

public boolean applyMove(int row, int col, boolean countMove) {
    if (!isValidPosition(row, col)) {
        return false;
    }

    if (countMove) {
        moveCount++;
    }

    toggleCells(row, col);
    return true;
}

private void toggleCells(int row, int col) {
    toggle(row, col);
    if (row > 0) toggle(row - 1, col);
    if (row < gridSize-1) toggle(row+1, col);
    if (col > 0) toggle(row, col-1);
    if (col < gridSize-1) toggle(row, col+1);
}

private void toggle(int row, int col) {
    board[row][col] = !board[row][col];
}

public boolean isValidPosition(int row, int col) {
    return row >=0 && row < gridSize && col >=0 && col < gridSize;
}

public boolean gameWon() {
    for (int row = 0; row < gridSize; row++) {
        for (int col = 0; col < gridSize; col++) {
            if (board[row][col]) {
                return false;
            }
        }
    }
    return true;
}
public boolean[][] getBoard() {
    return board;
}

public int getGridSize() {
    return gridSize;
}

public int getMoveCount() {
    return moveCount;
}

public String getGameMode() {
    return gameMode;
}
}
