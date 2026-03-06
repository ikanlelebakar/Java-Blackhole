package com.blackhole;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Blackhole Sim 2D");

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        primaryStage.getIcons().add(icon);

        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-container");

        // Import From different File
        customTitleBar titleBar = new customTitleBar(primaryStage);
        blackholeAssets blackhole1 = new blackholeAssets(1.4, 2.05);
        blackholeAssets blackhole2 = new blackholeAssets(3.0, 2.05);
        blackholeAssets blackhole3 = new blackholeAssets(2.0, 6);
        starAssets star = new starAssets(
            primaryStage,
            blackhole1,
            blackhole2,
            blackhole3
        );

        StackPane simulationArea = new StackPane();
        simulationArea
            .getChildren()
            .addAll(blackhole1, blackhole2, blackhole3, star);

        root.setTop(titleBar);
        root.setCenter(simulationArea);

        Scene scene = new Scene(root, 1000, 700);
        scene.setFill(Color.TRANSPARENT);

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        scene
            .getStylesheets()
            .add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
