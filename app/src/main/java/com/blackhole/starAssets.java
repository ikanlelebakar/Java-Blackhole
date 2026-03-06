package com.blackhole;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class starAssets extends Pane {

    private Stage stage;

    // Kecepatan awal bintang saat memancar dari titik klik
    public final double VELOCITY = 300.0;

    private blackholeAssets[] blackholes;

    // --- 1. MEMBUAT CLASS BARU KHUSUS UNTUK DATA BINTANG ---
    // Ini menggantikan array lama agar kita bisa memunculkan bintang sebanyak apapun
    private class Star {

        Circle shape;
        Polyline trail;
        double x, y;
        double vx, vy;
        boolean consumed = false;

        public Star(
            double startX,
            double startY,
            double startVx,
            double startVy
        ) {
            this.x = startX;
            this.y = startY;
            this.vx = startVx;
            this.vy = startVy;

            shape = new Circle(3, Color.WHITE);
            shape.setCenterX(x);
            shape.setCenterY(y);

            trail = new Polyline();
            trail.setStroke(Color.WHITE);
            trail.setStrokeWidth(1.5);
            trail.getPoints().addAll(x, y);
        }
    }

    // List dinamis untuk menyimpan bintang-bintang yang sedang aktif di layar
    private List<Star> activeStars = new ArrayList<>();

    public starAssets(Stage stage, blackholeAssets... blackholes) {
        this.stage = stage;
        this.blackholes = blackholes;

        // Memastikan layar penuh bisa menangkap klik mouse
        this.setStyle("-fx-background-color: transparent;");

        // --- 2. DETEKSI KLIK MOUSE UNTUK MEMUNCULKAN BINTANG ---
        this.setOnMouseClicked(event -> {
            // Memanggil fungsi untuk memunculkan 36 bintang (menyebar ke segala arah) di koordinat klik
            spawnStars(event.getX(), event.getY(), 36);
        });

        javafx.application.Platform.runLater(() -> {
            motionObject();
        });
    }

    // --- 3. FUNGSI UNTUK MENYEBARKAN BINTANG ---
    public void spawnStars(double startX, double startY, int count) {
        double angleStep = 360.0 / count; // Membagi lingkaran penuh (360 derajat)

        for (int i = 0; i < count; i++) {
            // Konversi sudut ke radian (karena Java Math menggunakan radian)
            double angle = Math.toRadians(i * angleStep);

            // Rumus Trigonometri untuk memecah kecepatan ke arah X dan Y (lingkaran)
            double startVx = VELOCITY * Math.cos(angle);
            double startVy = VELOCITY * Math.sin(angle);

            // Buat bintang baru dan masukkan ke dalam layar
            Star newStar = new Star(startX, startY, startVx, startVy);
            activeStars.add(newStar);
            getChildren().addAll(newStar.trail, newStar.shape);
        }
    }

    public void motionObject() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double screenWidth = stage.getScene().getWidth();
                double screenHeight = stage.getScene().getHeight();

                // Gunakan Iterator untuk me-loop daftar bintang.
                // Iterator memungkinkan kita MENGHAPUS bintang dengan aman jika dia sudah mati.
                Iterator<Star> iterator = activeStars.iterator();

                while (iterator.hasNext()) {
                    Star star = iterator.next();

                    if (!star.consumed) {
                        int subSteps = 10;
                        double subDT = PhysicsHelper.DT / subSteps;

                        for (int step = 0; step < subSteps; step++) {
                            double totalAccelerationX = 0;
                            double totalAccelerationY = 0;
                            boolean isSwallowed = false;

                            for (blackholeAssets bh : blackholes) {
                                double bhX = bh.getCenterX();
                                double bhY = bh.getCenterY();
                                double bhMass = bh.getMass();
                                double eventHorizonRadius =
                                    bh.getEventHorizonRadius();

                                double distance =
                                    PhysicsHelper.calculateDistance(
                                        star.x,
                                        star.y,
                                        bhX,
                                        bhY
                                    );

                                if (distance <= eventHorizonRadius) {
                                    isSwallowed = true;
                                    break;
                                }

                                double acceleration =
                                    PhysicsHelper.calculateGravityAcceleration(
                                        bhMass,
                                        distance
                                    );
                                double deltaX = bhX - star.x;
                                double deltaY = bhY - star.y;

                                double distanceForDir = Math.max(distance, 0.1);

                                totalAccelerationX +=
                                    (deltaX / distanceForDir) * acceleration;
                                totalAccelerationY +=
                                    (deltaY / distanceForDir) * acceleration;
                            }

                            if (isSwallowed) {
                                star.consumed = true;
                                break;
                            }

                            star.vx += totalAccelerationX * subDT;
                            star.vy += totalAccelerationY * subDT;

                            star.x += star.vx * subDT;
                            star.y += star.vy * subDT;

                            star.trail.getPoints().addAll(star.x, star.y);
                        }

                        if (!star.consumed) {
                            star.shape.setCenterX(star.x);
                            star.shape.setCenterY(star.y);

                            while (star.trail.getPoints().size() > 8000) {
                                star.trail.getPoints().remove(0, 2);
                            }

                            // Beri jarak toleransi (-200 sampai +200) agar cahaya di pinggir tidak langsung mati
                            if (
                                star.x < -200 ||
                                star.x > screenWidth + 200 ||
                                star.y < 4 ||
                                star.y > screenHeight + 200
                            ) {
                                star.consumed = true;
                            }
                        }
                    }

                    // --- 4. MEMBERSIHKAN MEMORI ---
                    // Jika bintang sudah mati (tertelan / keluar layar), hapus dari memori
                    if (star.consumed) {
                        getChildren().removeAll(star.shape); // Hapus visual dari layar
                        iterator.remove(); // Hapus data dari RAM komputer agar tidak ngelag
                    }
                }
            }
        };

        timer.start();
    }
}
