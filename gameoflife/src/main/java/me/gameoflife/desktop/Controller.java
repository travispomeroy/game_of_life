package me.gameoflife.desktop;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Controller {

    private static final Paint BLUE = Paint.valueOf("4680e3");
    private static final Paint WHITE = Paint.valueOf("fafbfc");

    @FXML
    private GridPane gridPane;

    public void runApplication() {
//        ObservableList<Node> children = gridPane.getChildren();
//        children.stream()
//                .map(node -> (Rectangle)node)
//                .forEach(this::toggleCell);

        start();
    }

    private void toggleCell(Rectangle rectangle) {
        if (rectangle.getFill().equals(BLUE)) {
            rectangle.setFill(WHITE);
        } else if (rectangle.getFill().equals(WHITE)) {
            rectangle.setFill(BLUE);
        }
    }

    private  boolean[][] initialBoard = new boolean[5][5];
    private boolean[][] destinationBoard = new boolean[5][5];

    public Controller() {
        initialBoard[1][2] = true;
        initialBoard[2][2] = true;
        initialBoard[3][2] = true;
    }

    public void start() {


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


            printBoard();

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

    private void printBoard() {
//        for (int i = 0; i < destinationBoard.length; i++) {
//            for (int j = 0; j < destinationBoard.length; j++) {
//                if (destinationBoard[i][j]) {
//                    gridPane.getChildren()
//                            .stream()
//                            .map()
//                }
//            }
//        }
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
}
