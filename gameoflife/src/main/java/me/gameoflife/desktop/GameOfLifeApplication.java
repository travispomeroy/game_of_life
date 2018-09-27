package me.gameoflife.desktop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameOfLifeApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Life");

        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();

        Button button = new Button("Button");
        Button button1 = new Button("Button 2");
        Button button2 = new Button("Button 3");

        Rectangle rectangle = new Rectangle(20, 20, Color.BLUE);

        GridPane.setConstraints(button, 1, 1);
        GridPane.setConstraints(button1, 1, 2);
        GridPane.setConstraints(button2, 1, 4);
        GridPane.setConstraints(rectangle, 1, 3);

        gridPane.getChildren().addAll(button, button1, rectangle, button2);

        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 300, 300);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
