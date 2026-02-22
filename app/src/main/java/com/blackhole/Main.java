package com.blackhole;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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

        CustomTitleBar titleBar = new CustomTitleBar(primaryStage);

        root.setTop(titleBar);

        Scene scene = new Scene(root, 800, 600);
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
