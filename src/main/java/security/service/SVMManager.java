package security.service;

import security.model.Point;
import java.util.List;

/**
 * Maksimum Güvenlik Koridoru (Maximum Margin) hesaplayan Linear SVM motoru.
 */
public class SVMManager {
    private double w1 = 0, w2 = 0, b = 0;
    private final double learningRate = 0.001;
    private final double lambda = 0.01; // Koridor genişliğini optimize eden katsayı
    private final int epochs = 10000;

    public void train(List<Point> points) {
        // [ZAMAN KARMAŞIKLIĞI: O(E * N)]
        for (int i = 0; i < epochs; i++) {
            for (Point p : points) {
                // Hinge Loss tabanlı Gradyan İnişi
                double condition = p.label() * (w1 * p.x() + w2 * p.y() - b);

                if (condition >= 1) {
                    w1 -= learningRate * (2 * lambda * w1);
                    w2 -= learningRate * (2 * lambda * w2);
                } else {
                    w1 -= learningRate * (2 * lambda * w1 - p.label() * p.x());
                    w2 -= learningRate * (2 * lambda * w2 - p.label() * p.y());
                    b -= learningRate * p.label();
                }
            }
        }
    }

    /**
     * Terminale matematiksel analiz çıktılarını verir.
     */
    public void printResults() {
        double marginWidth = 2.0 / Math.sqrt(w1 * w1 + w2 * w2);
        System.out.println("\n=== OTONOM ARAÇ GÜVENLİK ANALİZİ ===");
        System.out.println("Eğitim Durumu: BAŞARILI");
        System.out.println("Optimum Denklem: " + String.format("%.2f", w1) + "x + " + String.format("%.2f", w2) + "y - " + String.format("%.2f", b) + " = 0");
        System.out.println("Güvenlik Koridoru Genişliği (Margin): " + String.format("%.4f", marginWidth) + " birim");
        System.out.println("Zaman Karmaşıklığı: Tahmin aşaması O(1) - Gerçek Zamanlı Uygun");
        System.out.println("====================================\n");
    }

    public double getY(double x) { return (b - w1 * x) / w2; }
    public double getUpperMarginY(double x) { return (b + 1 - w1 * x) / w2; }
    public double getLowerMarginY(double x) { return (b - 1 - w1 * x) / w2; }
}
