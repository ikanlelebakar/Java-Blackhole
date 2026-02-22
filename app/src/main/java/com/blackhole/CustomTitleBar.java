package com.blackhole;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CustomTitleBar extends HBox {

    private double xOffset = 0;
    private double yOffset = 0;

    public CustomTitleBar(Stage stage) {
        this.getStyleClass().add("title-bar");
        this.setPrefHeight(30);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(10);

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        ImageView logoView = new ImageView(icon);
        logoView.setFitHeight(20);
        logoView.setFitWidth(20);
        logoView.setPreserveRatio(true);

        Font font = Font.loadFont(
            getClass().getResourceAsStream(
                "/fonts/JetBrainsMonoNerdFont-Regular.ttf"
            ),
            12
        );
        Label titleLabel = new Label("Blackhole Sim 2D");
        if (font != null) {
            titleLabel.setFont(font);
        }
        titleLabel.getStyleClass().add("title-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button closeBtn = new Button("X");
        closeBtn.getStyleClass().add("close-button");
        closeBtn.setPrefSize(45, 30);
        closeBtn.setOnAction(e -> Platform.exit());

        this.getChildren().addAll(logoView, titleLabel, spacer, closeBtn);

        this.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        this.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
