package com.blackhole;

public class PhysicsHelper {

    public static final double DT = 1.0 / 60.0;
    public static final double SOFTENING = 100.0; // Mencegah akselerasi tak hingga di pusat
    public static final double G = 10.0; // Konstanta G fiktif untuk pixel

    public static double calculateDistance(
        double x1,
        double y1,
        double x2,
        double y2
    ) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double calculateGravityAcceleration(
        double mass,
        double distance
    ) {
        double newtonianForce = (G * mass) / (distance * distance + SOFTENING);

        // 2. Simulasi Relativitas Umum Einstein (1/r^3)
        // Memberikan gaya tarik ekstra kuat di jarak sangat dekat agar lintasan bisa melingkar (loop)
        double relativityMultiplier = 400.0; // Kamu bisa menaikkan/menurunkan angka ini untuk mengatur jumlah loop
        double einsteinForce =
            (G * mass * relativityMultiplier) /
            (distance * distance * distance + SOFTENING);

        // Gabungkan kedua gaya tersebut
        return newtonianForce + einsteinForce;
    }

    public static double calculateEscapeVelocity(double mass, double distance) {
        return Math.sqrt((2 * G * mass) / distance);
    }
}
