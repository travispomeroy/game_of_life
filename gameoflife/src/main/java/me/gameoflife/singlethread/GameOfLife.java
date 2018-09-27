package me.gameoflife.singlethread;

public class GameOfLife {

    private boolean[][] initialBoard = new boolean[5][5];
    private boolean[][] destinationBoard = new boolean[5][5];

    GameOfLife() {
        initialBoard[1][2] = true;
        initialBoard[2][2] = true;
        initialBoard[3][2] = true;
    }

    public void start() {
        printBoard(initialBoard);

        while (true) {
            for (int row = 0; row < initialBoard.length; row++) {
                for (int col = 0; col < initialBoard.length; col++) {
                    int numberOfNeighbors = getNumberOfNeighbors(row, col);

                    if (initialBoard[row][col]) {
                        destinationBoard[row][col] = true;

                        if (numberOfNeighbors < 2) {
                            destinationBoard[row][col] = false;
                        }

                        if (numberOfNeighbors > 3) {
                            destinationBoard[row][col] = false;
                        }
                    } else {
                        destinationBoard[row][col] = false;

                        if (numberOfNeighbors == 3) {
                            destinationBoard[row][col] = true;
                        }
                    }
                }
            }

            printBoard(destinationBoard);

            System.out.println("--------------------------");
            initialBoard = destinationBoard;
            destinationBoard = new boolean[5][5];
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printBoard(boolean[][] boardToPrint) {
        for (int i = 0; i < boardToPrint.length; i++) {
            for (int j = 0; j < boardToPrint.length; j++) {
                if (boardToPrint[i][j]) {
                    System.out.print("O");
                } else {
                    System.out.print("x");
                }
            }
            System.out.println();
        }
    }

    private int getNumberOfNeighbors(int row, int col) {
        int neighborCount = 0;
        for (int leftIndex = -1; leftIndex < 2; leftIndex++) {
            for (int topIndex = -1; topIndex < 2; topIndex++) {
                if ((leftIndex == 0) && (topIndex == 0)) {
                    continue;
                }

                int neighbourRowIndex = row + leftIndex;
                int neighbourColIndex = col + topIndex;

                if (neighbourRowIndex < 0) {
                    neighbourRowIndex = initialBoard.length + neighbourRowIndex;
                }

                if (neighbourColIndex < 0) {
                    neighbourColIndex = initialBoard[0].length + neighbourColIndex;
                }

                boolean neighbour =
                        initialBoard[neighbourRowIndex % initialBoard.length][neighbourColIndex % initialBoard[0].length];

                if (neighbour) {
                    neighborCount++;
                }
            }
        }

        return neighborCount;
    }

    public static void main(String[] args) {

        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.start();

    }
}
