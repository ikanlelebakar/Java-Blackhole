package com.blackhole;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class blackholeAssets extends Pane {

    private Circle blackhole;
    private Circle eventHorizon;
    private final double mass = 200000;
    private final double eventHorizonRadius = 60.0;
    private final double blackholeRadius = 50.0;

    // Tambahkan variabel untuk mengatur posisi
    private double xPositionRatio;
    private double yPositionRatio;

    // Ubah constructor untuk menerima rasio posisi layar
    public blackholeAssets(double xPosRatio, double yPosRatio) {
        this.xPositionRatio = xPosRatio;
        this.yPositionRatio = yPosRatio;
        blackholeShape();
    }

    public void blackholeShape() {
        blackhole = new Circle();
        blackhole.setRadius(blackholeRadius);
        // Posisi sekarang mengikuti rasio yang dikirim dari Main.java
        blackhole
            .centerXProperty()
            .bind(widthProperty().divide(xPositionRatio));
        blackhole
            .centerYProperty()
            .bind(heightProperty().divide(yPositionRatio));
        blackhole.getStyleClass().add("blackhole-shape");

        eventHorizon = new Circle();
        eventHorizon.setRadius(eventHorizonRadius);
        eventHorizon.centerXProperty().bind(blackhole.centerXProperty());
        eventHorizon.centerYProperty().bind(blackhole.centerYProperty());
        eventHorizon.getStyleClass().add("event-horizon");

        getChildren().addAll(eventHorizon, blackhole);
    }

    public Circle getBlackhole() {
        return blackhole;
    }

    public Circle getEventHorizon() {
        return eventHorizon;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return blackhole.getRadius();
    }

    public double getEventHorizonRadius() {
        return eventHorizon.getRadius();
    }

    public double getBlackholeRadius() {
        return blackhole.getRadius();
    }

    public double getCenterX() {
        return blackhole.getCenterX();
    }

    public double getCenterY() {
        return blackhole.getCenterY();
    }
}
